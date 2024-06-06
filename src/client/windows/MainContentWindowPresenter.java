package client.windows;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import client.manager.ThreadManager;
import shared.classes.Task;
import shared.messages.*;
import shared.options.Options;
import java.util.Collections;
import java.util.Comparator;

public class MainContentWindowPresenter {
	private MainContentWindowView mainContView; // view for this presenter
	private Message dataMessage; // message that will be sent to server
	private CheckConnectionMessage checkConnectionMessage; // check connection message
	private Timer updateDataTimer; // Timer that checks if data was updated
	private Timer checkConnectionTimer; // Timer that checks if connection is available
	private ThreadManager threadManager; // Thread manager to send messages to server
	private Date dateChosenByUser;
	private int userID;
	
	private ArrayList<Task> savedTasksForThisUser;
	
	public MainContentWindowPresenter(MainContentWindowView mainContView) {
		super();
		this.mainContView = mainContView;
		this.initialize();
	}
	
	private MainContentWindowView getMainContView() {
		return mainContView;
	}

	private void setMainContView(MainContentWindowView mainContView) {
		this.mainContView = mainContView;
	}

	private Message getDataMessage() {
		return dataMessage;
	}

	private void setDataMessage(Message dataMessage) {
		this.dataMessage = dataMessage;
	}

	private CheckConnectionMessage getCheckConnectionMessage() {
		return checkConnectionMessage;
	}

	private void setCheckConnectionMessage(CheckConnectionMessage checkConnectionMessage) {
		this.checkConnectionMessage = checkConnectionMessage;
	}

	private Timer getUpdateDataTimer() {
		return updateDataTimer;
	}

	private void setUpdateDataTimer(Timer updateDataTimer) {
		this.updateDataTimer = updateDataTimer;
	}

	private Timer getCheckConnectionTimer() {
		return checkConnectionTimer;
	}

	private void setCheckConnectionTimer(Timer checkConnectionTimer) {
		this.checkConnectionTimer = checkConnectionTimer;
	}

	private ThreadManager getThreadManager() {
		return threadManager;
	}

	private void setThreadManager(ThreadManager threadManager) {
		this.threadManager = threadManager;
	}

	public Date getDateChosenByUser() {
		return dateChosenByUser;
	}

	public void setDateChosenByUser(Date dateChosenByUser) {
		this.dateChosenByUser = dateChosenByUser;
	}

	public ArrayList<Task> getSavedTasksForThisUser() {
		return savedTasksForThisUser;
	}

	public void setSavedTasksForThisUser(ArrayList<Task> savedTasksForThisUser) {
		this.savedTasksForThisUser = savedTasksForThisUser;
	}

	private void initialize() {
		System.out.println("CLIENT_SIDE:Initializing signed in application");
		this.setDateChosenByUser(new Date());
		this.setDataMessage(new CheckConnectionMessage());
		this.setCheckConnectionMessage(new CheckConnectionMessage());
		this.setThreadManager(new ThreadManager(this.getCheckConnectionMessage()));	
		this.setUpdateDataTimer(new Timer(Options.CHECK_FOR_UPDATED_DATA_INTERVAL, new UpdateDataTimerListener())); 
		this.setCheckConnectionTimer(new Timer(Options.CHECK_FOR_CONNECTION_INTERVAL, new CheckConnectionTimerListener()));
		this.getMainContView().getManageTasksWindowButton().addActionListener(new ManageTasksWindowButtonListener());
		this.getMainContView().getTasksForDayWindowButton().addActionListener(new TasksForDayWindowButtonListener());
		this.getMainContView().getTasksForWeekWindowButton().addActionListener(new TasksForWeekWindowButtonListener());
		this.getMainContView().getRepeatingTaskWindowButton().addActionListener(new RepeatingTaskWindowButtonListener());
		this.getMainContView().getStatisticWindowButton().addActionListener(new StatisticsWindowButtonListener());
		this.getMainContView().getExitButton().addActionListener(new ExitButtonListener());
		this.getMainContView().getAddTaskTasksForTodayWindowButton().addActionListener(new addTaskTasksForTodayWindowButtonListener());
		this.getMainContView().getCalendar().addPropertyChangeListener(new ChooseNewDateInCalendarListener());
		this.getMainContView().getDeleteTaskTasksForTodayWindowButton().addActionListener(new deleteTaskTasksForTodayWindowButtonListener());
		this.getMainContView().getChangeIsFinishedPropertyToOppositeTasksForTodayWindowButton().addActionListener(new changeIsFinishedPropertyToOppositeTasksForTodayWindowButtonListener());
		this.getMainContView().getChooseDayStatisticsPanelComboBox().addActionListener(new chooseDayStatisticsPanelComboBoxListener());
		this.getMainContView().getManageTasksForMultipleDaysWindowDeleteButton().addActionListener(new ManageTasksForMultipleDaysWindowDeleteButton());
		this.getMainContView().getManageTasksForMultipleDaysWindowAddButton().addActionListener(new ManageTasksForMultipleDaysWindowAddButton());
		this.getMainContView().getChooseDifferentDayTasksForTodayWindowButton().addActionListener(new ChooseDifferentDayTasksForTodayWindowButtonListener());
		
		String newLableText = "the ";
    	newLableText += convertDateToDayAsString(getDateChosenByUser());
    	newLableText += " of ";
    	newLableText += convertMonthAsNumberToMonthAsString(getDateChosenByUser());
    	newLableText += " " + String.valueOf(getDateChosenByUser().getYear() + 1900);
    	getMainContView().getChosenDayByUserTaskForDayWindowLable().setText(newLableText);
    	getMainContView().getCurrentChosenDateByUserChooseDateWindowLabel().setText(newLableText);
    	getMainContView().getTodayIsTaskForWeekWindowLabel().setText("Today is " + newLableText);
		this.getUpdateDataTimer().start();
		this.getCheckConnectionTimer().start();
		
		while(!this.getCheckConnectionTimer().isRunning()) {
			
		}
		setDataMessage(new GetAllTasksForThisUserMessage(this.getMainContView().getUserID()));
		getThreadManager().sendMessage(getDataMessage());
		
		System.out.println("CLIENT_SIDE:Check info timer has started");
		System.out.println("CLIENT_SIDE:Client side initialized successfully");
		for (int i = 0; i < 1000; i++) {
			System.out.print("-");
		}
		System.out.println("/n");
	}
	
	public void createNewTask(Date dateForTask, String title) {
		System.out.println("CLIENT_SIDE-ACTION:Create new task button pressed");
		setDataMessage(new AddNewTaskMessage(this.getMainContView().getUserID(), new Task(title, dateForTask, false)));
		getThreadManager().sendMessage(getDataMessage());
	}
	
	/**
	 * Timer that checks if data is updated
	 */
	private class UpdateDataTimerListener implements ActionListener{
		/**
		 * This part of code is called by UpdateDataTimer
		 * Checks whether data was updated
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(getCheckConnectionMessage().isDataUpdated()) { // If data  in check connection message is updated
				if(getCheckConnectionMessage().getResponseState() == ResponseState.SUCCESS) { // And response is successful
					mainContView.getChosseDateConnectionIndicator().setBackground(Color.GREEN);
					mainContView.getRepeatingTasksConnectionIndicator().setBackground(Color.GREEN);
					mainContView.getStatisticsConnectionIndicator().setBackground(Color.GREEN);
					mainContView.getTasksForTodayConnectionIndicator().setBackground(Color.GREEN);
					mainContView.getTasksForWeekConnectionIndicator().setBackground(Color.GREEN);
				} else {
					mainContView.getChosseDateConnectionIndicator().setBackground(Color.RED);
					mainContView.getRepeatingTasksConnectionIndicator().setBackground(Color.RED);
					mainContView.getStatisticsConnectionIndicator().setBackground(Color.RED);
					mainContView.getTasksForTodayConnectionIndicator().setBackground(Color.RED);
					mainContView.getTasksForWeekConnectionIndicator().setBackground(Color.RED);
				}
				getCheckConnectionMessage().setDataUpdated(false); // Uncheck data update
			}
			if(dataMessage.isDataUpdated()) { // If data was updated 
				if(dataMessage instanceof AddNewTaskMessage) {
					AddNewTaskMessage castedData = (AddNewTaskMessage) dataMessage;
					if(dataMessage.getResponseState() == ResponseState.SUCCESS) {
						System.out.println("New task inserted successfuly");
					} else if (dataMessage.getResponseState() == ResponseState.DATE_ALREADY_OCCUPIED) {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "This time is already ocuppied, please delete task sceduled for this time!", "Could not create task", JOptionPane.WARNING_MESSAGE);
					} else if (dataMessage.getResponseState() == ResponseState.ERROR) {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Error occured!!!\nContact developer for fix!", "Could not create task", JOptionPane.ERROR_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Unhandled error!!!\nContact developer for fix!", "Unhandled error!!!", JOptionPane.ERROR_MESSAGE);
					}
					
					setDataMessage(new GetAllTasksForThisUserMessage(getMainContView().getUserID()));
	        		getThreadManager().sendMessage(getDataMessage());
					
				} else if(dataMessage instanceof GetAllTasksForThisUserMessage) {
					GetAllTasksForThisUserMessage castedData = (GetAllTasksForThisUserMessage) dataMessage;
					DefaultTableModel tableOfTasksTableModel = new DefaultTableModel(
							new Object[][] {
							},
							new String[] {"Task Title", "Time", "IsCompleted"}	
						);
					
					ArrayList<Task> tasksToBeShown = new ArrayList<Task>();
					for(Task task : castedData.getTasks()) {
						if(getDateChosenByUser().getDate() == task.getDate().getDate() &&
						   getDateChosenByUser().getMonth() == task.getDate().getMonth() &&
						   getDateChosenByUser().getYear() == task.getDate().getYear()) {
						   tasksToBeShown.add(task);
						}
					}
					
					setSavedTasksForThisUser(castedData.getTasks());
					
					
					tasksToBeShown.sort(Comparator.comparing(Task::getDate));
					
					for(Task task : tasksToBeShown) {	
						String time = task.getDate().getHours() < 10 ? "0" + String.valueOf(task.getDate().getHours()) : String.valueOf(task.getDate().getHours());
						time += ":";
						time += task.getDate().getMinutes() < 10 ? "0" + String.valueOf(task.getDate().getMinutes()) : String.valueOf(task.getDate().getMinutes());
						time += ":";
						time += task.getDate().getSeconds() < 10 ? "0" + String.valueOf(task.getDate().getSeconds()) : String.valueOf(task.getDate().getSeconds());
						
						Object[] row = {task.getTitle(), time, task.isIs_completed()};
						tableOfTasksTableModel.addRow(row);
					}
					getMainContView().getTableOfTasks().setModel(tableOfTasksTableModel);
					String selectedItem = (String) getMainContView().getChooseDayStatisticsPanelComboBox().getSelectedItem();
					changeStatisticsData(selectedItem);
					
					DefaultTableModel forWeekTableModel = new DefaultTableModel(
							new Object[][] {
								{null, null, null, null, null, null, null},
							},
							new String[] {
								"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
							}
						);
					
					Object[] row = {"Completed:", "Completed:", "Completed:", "Completed:", "Completed:", "Completed:", "Completed:"};
					forWeekTableModel.addRow(row);
					
					Date userDate = getDateChosenByUser();
					
					int mondayIndex = 0;
		    		int sundayIndex = 0;
		    		switch(userDate.getDay()) {
		    		case 0:
		    			mondayIndex = 6;
		    			sundayIndex = 0;
		    			break;
		    		case 1:
		    			mondayIndex = 0;
		    			sundayIndex = 6;
		    			break;
		    		case 2:
		    			mondayIndex = 1;
		    			sundayIndex = 5;
		    			break;
		    		case 3:
		    			mondayIndex = 2;
		    			sundayIndex = 4;
		    			break;
		    		case 4:
		    			mondayIndex = 3;
		    			sundayIndex = 3;
		    			break;
		    		case 5:
		    			mondayIndex = 4;
		    			sundayIndex = 2;
		    			break;
		    		case 6:
		    			mondayIndex = 5;
		    			sundayIndex = 1;
		    			break;
		    		}
					
					Date monday = new Date(userDate.getTime() - mondayIndex * Date.UTC(70, 0, 2, 0, 0, 0));
					Date sunday = new Date(userDate.getTime() + sundayIndex * Date.UTC(70, 0, 2, 0, 0, 0));
					Date currentTime = new Date();

					monday.setHours(0);
					monday.setMinutes(0);
					monday.setSeconds(0);
					sunday.setHours(23);
					sunday.setMinutes(59);
					sunday.setSeconds(59);
					
					int mondayTasks[] = {0, 0, 0}; // First is finished second is failed third is to be completed
					int thuesdayTasks[] = {0, 0, 0};
					int wednesdayTasks[] = {0, 0, 0};
					int thursdayTasks[] = {0, 0, 0};
					int fridayTasks[] = {0, 0, 0};
					int saturdayTasks[] = {0, 0, 0};
					int sundayTasks[] = {0, 0, 0};
					
					for(Task task : castedData.getTasks()) {
						if(task.getDate().getTime() >= monday.getTime() && task.getDate().getTime() <= sunday.getTime()) { // All tasks for this week
							if(task.getDate().getTime() > currentTime.getTime()) { // If task is in the future
								System.out.println("Task in the future:" + task);
								if(task.isIs_completed()) {
									switch(task.getDate().getDay()) {
									case 0: // Sunday
										sundayTasks[0]++;
										break;
									case 1: // Monday
										mondayTasks[0]++;
										break;
									case 2: // Tuesday
										thuesdayTasks[0]++;
										break;
									case 3: // Wednesday
										wednesdayTasks[0]++;
										break;
									case 4: // Thursday
										thursdayTasks[0]++;
										break;
									case 5: // Friday
										fridayTasks[0]++;
										break;
									case 6: // Saturday
										saturdayTasks[0]++;
										break;
									}
								} else { // To be completed tasks
									switch(task.getDate().getDay()) {
									case 0: // Sunday
										sundayTasks[2]++;
										break;
									case 1: // Monday
										mondayTasks[2]++;
										break;
									case 2: // Tuesday
										thuesdayTasks[2]++;
										break;
									case 3: // Wednesday
										wednesdayTasks[2]++;
										break;
									case 4: // Thursday
										thursdayTasks[2]++;
										break;
									case 5: // Friday
										fridayTasks[2]++;
										break;
									case 6: // Saturday
										saturdayTasks[2]++;
										break;
									}
								}
									
							} else { // Task is in the past
								if(task.isIs_completed()) {
									switch(task.getDate().getDay()) {
									case 0: // Sunday
										sundayTasks[0]++;
										break;
									case 1: // Monday
										mondayTasks[0]++;
										break;
									case 2: // Tuesday
										thuesdayTasks[0]++;
										break;
									case 3: // Wednesday
										wednesdayTasks[0]++;
										break;
									case 4: // Thursday
										thursdayTasks[0]++;
										break;
									case 5: // Friday
										fridayTasks[0]++;
										break;
									case 6: // Saturday
										saturdayTasks[0]++;
										break;
									}
								} else { // To be completed tasks
									switch(task.getDate().getDay()) {
									case 0: // Sunday
										sundayTasks[1]++;
										break;
									case 1: // Monday
										mondayTasks[1]++;
										break;
									case 2: // Tuesday
										thuesdayTasks[1]++;
										break;
									case 3: // Wednesday
										wednesdayTasks[1]++;
										break;
									case 4: // Thursday
										thursdayTasks[1]++;
										break;
									case 5: // Friday
										fridayTasks[1]++;
										break;
									case 6: // Saturday
										saturdayTasks[1]++;
										break;
									}
								}
							}
						}
					}
					
					row = new Object[]{String.valueOf(mondayTasks[0]), String.valueOf(thuesdayTasks[0]),
							           String.valueOf(wednesdayTasks[0]), String.valueOf(thursdayTasks[0]),
							           String.valueOf(fridayTasks[0]), String.valueOf(saturdayTasks[0]),
							           String.valueOf(sundayTasks[0])};
					forWeekTableModel.addRow(row);
					
					row = new Object[]{"Failed:", "Failed:", "Failed:", "Failed:", "Failed:", "Failed:", "Failed:"};
					forWeekTableModel.addRow(row);
					
					row = new Object[]{String.valueOf(mondayTasks[1]), String.valueOf(thuesdayTasks[1]),
					           String.valueOf(wednesdayTasks[1]), String.valueOf(thursdayTasks[1]),
					           String.valueOf(fridayTasks[1]), String.valueOf(saturdayTasks[1]),
					           String.valueOf(sundayTasks[1])};
					forWeekTableModel.addRow(row);
					
					row = new Object[]{"ToBeCompleted:", "ToBeCompleted:", "ToBeCompleted:", "ToBeCompleted:", "ToBeCompleted:", "ToBeCompleted:", "ToBeCompleted:"};
					forWeekTableModel.addRow(row);
					
					row = new Object[]{String.valueOf(mondayTasks[2]), String.valueOf(thuesdayTasks[2]),
					           String.valueOf(wednesdayTasks[2]), String.valueOf(thursdayTasks[2]),
					           String.valueOf(fridayTasks[2]), String.valueOf(saturdayTasks[2]),
					           String.valueOf(sundayTasks[2])};
					forWeekTableModel.addRow(row);
					
					mainContView.getTasksForWeekPanelDaysOfTheWeek().setModel(forWeekTableModel);
					
					
					
				} else if(dataMessage instanceof DeleteSelectedTasksMessage){
					DeleteSelectedTasksMessage castedData = (DeleteSelectedTasksMessage) dataMessage;
					if(castedData.getResponseState() == ResponseState.SUCCESS) {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Selected tasks were deleted succesfully", "Tasks deleted", JOptionPane.INFORMATION_MESSAGE);
					} else if (castedData.getResponseState() == ResponseState.NO_TASKS_WERE_DELETED) {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Selected tasks could not be deleted", "Tasks were not deleted!", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Error ocurred during deletion", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					
					setDataMessage(new GetAllTasksForThisUserMessage(getMainContView().getUserID()));
	        		getThreadManager().sendMessage(getDataMessage());
	        		
				} else if(dataMessage instanceof ChangeTasksStatesToOppositeMessage){
					ChangeTasksStatesToOppositeMessage castedData = (ChangeTasksStatesToOppositeMessage) dataMessage;
					
					if(castedData.getResponseState() == ResponseState.SUCCESS) {
						// If response was successful
					} else if (castedData.getResponseState() == ResponseState.NO_TASKS_WERE_CHANGED) {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Selected tasks could not be changed", "Tasks were not changed!", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(mainContView.getFrame(), "Error ocurred during deletion", "Error!", JOptionPane.ERROR_MESSAGE);
					}
					
					setDataMessage(new GetAllTasksForThisUserMessage(getMainContView().getUserID()));
	        		getThreadManager().sendMessage(getDataMessage());
	        		
				} else if(dataMessage instanceof ManageMultipleTasksMessage){
					ManageMultipleTasksMessage castedData = (ManageMultipleTasksMessage) dataMessage;
					
//					if(castedData.getResponseState() == ResponseState.SUCCESS) {
//						// If response was successful
//					} else if (castedData.getResponseState() == ResponseState.NO_TASKS_WERE_CHANGED) {
//						JOptionPane.showMessageDialog(mainContView.getFrame(), "Selected tasks could not be changed", "Tasks were not changed!", JOptionPane.WARNING_MESSAGE);
//					} else {
//						JOptionPane.showMessageDialog(mainContView.getFrame(), "Error ocurred during deletion", "Error!", JOptionPane.ERROR_MESSAGE);
//					}
					
					setDataMessage(new GetAllTasksForThisUserMessage(getMainContView().getUserID()));
	        		getThreadManager().sendMessage(getDataMessage());
	        		
				}else {
					System.out.println("Unknown message received!!!");
					System.out.println(dataMessage);
				}
				dataMessage.setDataUpdated(false); // Uncheck data update
			}
		}
	}
	
	
	/**
	 * Timer that checks if data is updated
	 */
	private class CheckConnectionTimerListener implements ActionListener{
		/**
		 * This part of code is called every 50 milliseconds to check if 
		 * data was updated
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			if(getThreadManager().isCheckConnectionMessageThreadAlive()) { // Check connection thread is alive		
				if(getThreadManager().getCheckConnectionThreadDuration() >= Options.MAX_TIME_RESPOND) { // Time has expired
					System.out.println("CLIENT_SIDE:ERROR:Time for response expired waiting for reconnect");
					mainContView.getChosseDateConnectionIndicator().setBackground(Color.RED);
					mainContView.getRepeatingTasksConnectionIndicator().setBackground(Color.RED);
					mainContView.getStatisticsConnectionIndicator().setBackground(Color.RED);
					mainContView.getTasksForTodayConnectionIndicator().setBackground(Color.RED);
					mainContView.getTasksForWeekConnectionIndicator().setBackground(Color.RED);
				} 	
			} else { // Check connection thread is dead
				setCheckConnectionMessage(new CheckConnectionMessage()); // Create a new check connection message
				getThreadManager().sendMessage(getCheckConnectionMessage());	// And send it to server			
			}
			
		}
		
	}
	
	/**
	 * Listener class for manageTasksWindowButton button
	 */
	private class ManageTasksWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when manageTasksWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) getMainContView().getLeftCardLayoutPanel().getLayout();
			cardLayout.show(getMainContView().getLeftCardLayoutPanel(), "ManageTasksWindow");
		}
		
	}
	
	/**
	 * Listener class for tasksForDayWindowButton button
	 */
	private class TasksForDayWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when tasksForDayWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) getMainContView().getLeftCardLayoutPanel().getLayout();
			cardLayout.show(getMainContView().getLeftCardLayoutPanel(), "TasksForDayWindow");
		}
		
	}
	
	/**
	 * Listener class for tasksForWeekWindowButton button
	 */
	private class TasksForWeekWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when tasksForWeekWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) getMainContView().getLeftCardLayoutPanel().getLayout();
			cardLayout.show(getMainContView().getLeftCardLayoutPanel(), "TasksForWeekWindow");
		}
		
	}
	
	/**
	 * Listener class for repeatingTaskWindowButton button
	 */
	private class RepeatingTaskWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when repeatingTaskWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) getMainContView().getLeftCardLayoutPanel().getLayout();
			cardLayout.show(getMainContView().getLeftCardLayoutPanel(), "RepeatingTasksWindow");
		}
		
	}
	
	/**
	 * Listener class for statisticsWindowButton button
	 */
	private class StatisticsWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when statisticsWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			CardLayout cardLayout = (CardLayout) getMainContView().getLeftCardLayoutPanel().getLayout();
			cardLayout.show(getMainContView().getLeftCardLayoutPanel(), "StatisticsWindow");
		}
		
	}
	
	/**
	 * Listener class for exitButton button
	 */
	private class ExitButtonListener implements ActionListener{
		
		/**
		 * Executes when exitButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int responce = JOptionPane.showOptionDialog(getMainContView().getFrame(), "Do you whish to exit from account?", "Exit?", 
					                     JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
					                     null, null, null);
			if(responce == JOptionPane.YES_OPTION) {
				System.out.println("User choose yes option");
				new LoginAndRegisterView();
				getMainContView().getFrame().dispose();
			} else {
				System.out.println("User choose no option");
			}
		}
		
	}
	
	/**
	 * Listener class for addTaskTasksForTodayWindowButton button
	 */
	private class addTaskTasksForTodayWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when addTaskTasksForTodayWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			new CreateNewTaskDialog(getDateChosenByUser(), getMainContView().getMainContWindowPres());
		}
		
	}
	
	/**
	 * Listener class for deleteTaskTasksForTodayWindowButton button
	 */
	private class deleteTaskTasksForTodayWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when deleteTaskTasksForTodayWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedOption = JOptionPane.showConfirmDialog(getMainContView().getFrame(), "Do you really want to delete selected tasks?", "Delete tasks", JOptionPane.OK_CANCEL_OPTION);
			if(selectedOption == JOptionPane.OK_OPTION) {
				int[] selectedRows = getMainContView().getTableOfTasks().getSelectedRows();
				if(selectedRows.length != 0) {
					ArrayList<Task> selectedTasks = new ArrayList<Task>();
					for (int i = 0; i < selectedRows.length; i++) {
						System.out.println("Selected row:" + selectedRows[i]);
						String titleOfSelectedTask = (String)getMainContView().getTableOfTasks().getValueAt(selectedRows[i], 0);
						String timeOfSelectedTask = (String)getMainContView().getTableOfTasks().getValueAt(selectedRows[i], 1);
						Date dateOfSelectedTask = (Date) getDateChosenByUser().clone();
						dateOfSelectedTask.setHours(Integer.parseInt("" + timeOfSelectedTask.charAt(0) + "" + timeOfSelectedTask.charAt(1)));
						dateOfSelectedTask.setMinutes(Integer.parseInt("" + timeOfSelectedTask.charAt(3) + "" + timeOfSelectedTask.charAt(4)));
						dateOfSelectedTask.setSeconds(Integer.parseInt("" + timeOfSelectedTask.charAt(6) + "" + timeOfSelectedTask.charAt(7)));
						
						boolean isTaskFinished = (boolean)getMainContView().getTableOfTasks().getValueAt(selectedRows[i], 2);
						
						selectedTasks.add(new Task(titleOfSelectedTask, dateOfSelectedTask, isTaskFinished));
					}
					
					setDataMessage(new DeleteSelectedTasksMessage(getMainContView().getUserID(), selectedTasks));
					getThreadManager().sendMessage(getDataMessage());
				} 
			}
		}	
	}
	
	/**
	 * Listener class for deleteTaskTasksForTodayWindowButton button
	 */
	private class changeIsFinishedPropertyToOppositeTasksForTodayWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when deleteTaskTasksForTodayWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			int[] selectedRows = getMainContView().getTableOfTasks().getSelectedRows();
			if(selectedRows.length != 0) {
				ArrayList<Task> selectedTasks = new ArrayList<Task>();
				for (int i = 0; i < selectedRows.length; i++) {
					System.out.println("Selected row:" + selectedRows[i]);
					String titleOfSelectedTask = (String)getMainContView().getTableOfTasks().getValueAt(selectedRows[i], 0);
					String timeOfSelectedTask = (String)getMainContView().getTableOfTasks().getValueAt(selectedRows[i], 1);
					Date dateOfSelectedTask = (Date) getDateChosenByUser().clone();
					dateOfSelectedTask.setHours(Integer.parseInt("" + timeOfSelectedTask.charAt(0) + "" + timeOfSelectedTask.charAt(1)));
					dateOfSelectedTask.setMinutes(Integer.parseInt("" + timeOfSelectedTask.charAt(3) + "" + timeOfSelectedTask.charAt(4)));
					dateOfSelectedTask.setSeconds(Integer.parseInt("" + timeOfSelectedTask.charAt(6) + "" + timeOfSelectedTask.charAt(7)));
					
					boolean isTaskFinished = (boolean)getMainContView().getTableOfTasks().getValueAt(selectedRows[i], 2);
					
					selectedTasks.add(new Task(titleOfSelectedTask, dateOfSelectedTask, isTaskFinished));
				}
				
				setDataMessage(new ChangeTasksStatesToOppositeMessage(getMainContView().getUserID(), selectedTasks));
				getThreadManager().sendMessage(getDataMessage());
			} 
		}		
	}
	
	/**
	 * Listener class for ManageTasksForMultipleDaysWindowAddButton button
	 */
	private class ManageTasksForMultipleDaysWindowAddButton implements ActionListener{
		
		/**
		 * Executes when manageTasksForMultipleDaysWindowAddButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Date fromDate = getMainContView().getManageTasksForMultipleDaysWindowFromDateChooser().getDate();
			Date toDate = getMainContView().getManageTasksForMultipleDaysWindowToDateChooser().getDate();
			Date time = (Date) getMainContView().getManageTasksForMultipleDaysWindowTimeSpinner().getValue();
			
			fromDate.setSeconds(time.getSeconds());
			fromDate.setMinutes(time.getMinutes());
			fromDate.setHours(time.getHours());
			toDate.setSeconds(toDate.getSeconds());
			toDate.setMinutes(toDate.getMinutes());
			toDate.setHours(toDate.getHours());
			
			if(fromDate.getTime() > toDate.getTime()) {
				JOptionPane.showMessageDialog(mainContView.getFrame(), "From time must go before to!", "Wrong time interval", JOptionPane.WARNING_MESSAGE);
			} else {
				setDataMessage(new ManageMultipleTasksMessage(getMainContView().getUserID(), fromDate, toDate, getMainContView().getManageTasksForMultipleDaysWindowTitleTextField().getText(), "ADD"));
				getThreadManager().sendMessage(getDataMessage());		
			}	
			
		}		
	}
	
	/**
	 * Listener class for ManageTasksForMultipleDaysWindowDeleteButton button
	 */
	private class ManageTasksForMultipleDaysWindowDeleteButton implements ActionListener{
		
		/**
		 * Executes when manageTasksForMultipleDaysWindowDeleteButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Date fromDate = getMainContView().getManageTasksForMultipleDaysWindowFromDateChooser().getDate();
			Date toDate = getMainContView().getManageTasksForMultipleDaysWindowToDateChooser().getDate();
			Date time = (Date) getMainContView().getManageTasksForMultipleDaysWindowTimeSpinner().getValue();
			
			fromDate.setSeconds(time.getSeconds());
			fromDate.setMinutes(time.getMinutes());
			fromDate.setHours(time.getHours());
			toDate.setSeconds(time.getSeconds());
			toDate.setMinutes(time.getMinutes());
			toDate.setHours(time.getHours());
			
			System.out.println("From date:" + fromDate.getTime() + " " + fromDate);
			System.out.println("To date:" + toDate.getTime() + " " + fromDate);
			
			if(fromDate.getTime() > toDate.getTime()) {
				JOptionPane.showMessageDialog(mainContView.getFrame(), "From time must go before to!", "Wrong time interval", JOptionPane.WARNING_MESSAGE);
			} else {
				setDataMessage(new ManageMultipleTasksMessage(getMainContView().getUserID(), fromDate, toDate, getMainContView().getManageTasksForMultipleDaysWindowTitleTextField().getText(), "DELETE"));
				getThreadManager().sendMessage(getDataMessage());		
			}		
		}		
	}
	
	/**
	 * Listener class for ManageTasksForMultipleDaysWindowDeleteButton button
	 */
	private class ChooseDifferentDayTasksForTodayWindowButtonListener implements ActionListener{
		
		/**
		 * Executes when manageTasksForMultipleDaysWindowDeleteButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			setDateChosenByUser(new Date());

        	String newLableText = "the ";
        	newLableText += convertDateToDayAsString(getDateChosenByUser());
        	newLableText += " of ";
        	newLableText += convertMonthAsNumberToMonthAsString(getDateChosenByUser());
        	newLableText += " " + String.valueOf(getDateChosenByUser().getYear() + 1900);
        	getMainContView().getChosenDayByUserTaskForDayWindowLable().setText(newLableText);
        	getMainContView().getCurrentChosenDateByUserChooseDateWindowLabel().setText(newLableText);
			
			setDataMessage(new GetAllTasksForThisUserMessage(getMainContView().getUserID()));
    		getThreadManager().sendMessage(getDataMessage());
		}		
	}
	
	/**
	 * Listener class for deleteTaskTasksForTodayWindowButton button
	 */
	private class chooseDayStatisticsPanelComboBoxListener implements ActionListener{
		
		/**
		 * Executes when deleteTaskTasksForTodayWindowButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			String selectedItem = (String) getMainContView().getChooseDayStatisticsPanelComboBox().getSelectedItem();
			changeStatisticsData(selectedItem);
		}		
	}
	
	/**
	 * Listener class for ChooseNewDateInCalendar calendar
	 */
	private class ChooseNewDateInCalendarListener implements PropertyChangeListener {
		
		/**
		 * Executes when calendar in chooseDateWindow is changed
		 */
		@Override
        public void propertyChange(PropertyChangeEvent evt) {
            // Check if the property changed is the "calendar" property
            if ("calendar".equals(evt.getPropertyName())) {
                // A new date was chosen, perform your action here
            	JCalendar chosenDate = (JCalendar) evt.getSource();
            	setDateChosenByUser(chosenDate.getDate());
            	String newLableText = "the ";
            	newLableText += convertDateToDayAsString(getDateChosenByUser());
            	newLableText += " of ";
            	newLableText += convertMonthAsNumberToMonthAsString(getDateChosenByUser());
            	newLableText += " " + String.valueOf(getDateChosenByUser().getYear() + 1900);
            	getMainContView().getChosenDayByUserTaskForDayWindowLable().setText(newLableText);
            	getMainContView().getCurrentChosenDateByUserChooseDateWindowLabel().setText(newLableText);
                System.out.println("New date chosen: " + chosenDate.getDate());
                setDataMessage(new GetAllTasksForThisUserMessage(getMainContView().getUserID()));
        		getThreadManager().sendMessage(getDataMessage());
                
            }
        }
		
	}
	
	public static String convertDateToDayAsString(Date date) {
		
		String dayAsString = String.valueOf(date.getDate());
    	
    	switch(date.getDate()) {
    	case 1:
    		dayAsString += "st";
    		break;
    	case 21:
    		dayAsString += "st";
    		break;
    	case 31:
    		dayAsString += "st";
    		break;
    	case 2:
    		dayAsString += "nd";
    		break;
    	case 22:
    		dayAsString += "nd";
    		break;
    	case 3:
    		dayAsString += "rd";
    		break;
    	case 23:
    		dayAsString += "rd";
    		break;
    	default:
    		dayAsString += "th";
    		break;
    	}
		return dayAsString;
	}
	
	public static String convertMonthAsNumberToMonthAsString(Date date) {
		switch(date.getMonth()) {
    	case 0:
    		return "January";
    	case 1:
    		return "February";
    	case 2:
    		return "March";
    	case 3:
    		return "April";
    	case 4:
    		return "May";
    	case 5:
    		return "June";
    	case 6:
    		return "July";
    	case 7:
    		return "August";
    	case 8:
    		return "September";	
    	case 9:
    		return "October";
    	case 10:
    		return "November";
    	case 11:
    		return "December";
    	default:
    		return "Unknown month";
    	}
	}
	
	private void changeStatisticsData(String option) {
		int completedTasks = 0;
		int failedTasks = 0;
		Date currentTime = new Date();
		Date userDate = getDateChosenByUser();
		
		
		switch(option) {
		case "Day":
			for(Task task : getSavedTasksForThisUser()) {
				Date taskDate = task.getDate();
				// If date is correct
				if(taskDate.getYear() == userDate.getYear() && taskDate.getMonth() == userDate.getMonth() && taskDate.getDay() == userDate.getDay()) {
					if(currentTime.getTime() <= taskDate.getTime()) { // If in the future
						if(task.isIs_completed()) {
							completedTasks++;
						}
					} else if (currentTime.getTime() > taskDate.getTime()) { // If in the past
						if(task.isIs_completed()) {
							completedTasks++;
						} else {
							failedTasks++;
						}
					}
				}
			}
    		break;
    		
    	case "Week":
    		int mondayIndex = 0;
    		int sundayIndex = 0;
    		switch(userDate.getDay()) {
    		case 0:
    			mondayIndex = 6;
    			sundayIndex = 0;
    			break;
    		case 1:
    			mondayIndex = 0;
    			sundayIndex = 6;
    			break;
    		case 2:
    			mondayIndex = 1;
    			sundayIndex = 5;
    			break;
    		case 3:
    			mondayIndex = 2;
    			sundayIndex = 4;
    			break;
    		case 4:
    			mondayIndex = 3;
    			sundayIndex = 3;
    			break;
    		case 5:
    			mondayIndex = 4;
    			sundayIndex = 2;
    			break;
    		case 6:
    			mondayIndex = 5;
    			sundayIndex = 1;
    			break;
    		}
    		
    		for(Task task : getSavedTasksForThisUser()) {
				Date taskDate = task.getDate();

				Date monday = new Date(userDate.getTime() - mondayIndex * Date.UTC(70, 0, 2, 0, 0, 0));
				Date sunday = new Date(userDate.getTime() + sundayIndex * Date.UTC(70, 0, 2, 0, 0, 0));

				monday.setHours(0);
				monday.setMinutes(0);
				monday.setSeconds(0);
				sunday.setHours(23);
				sunday.setMinutes(59);
				sunday.setSeconds(59);
				
				if(taskDate.getTime() >= monday.getTime() && taskDate.getTime() <= sunday.getTime()) {
					if(currentTime.getTime() <= taskDate.getTime()) { // If in the future
						if(task.isIs_completed()) {
							completedTasks++;
						}
					} else if (currentTime.getTime() > taskDate.getTime()) { // If in the past
						if(task.isIs_completed()) {
							completedTasks++;
						} else {
							failedTasks++;
						}
					}
				}
			}
    		
    		break;
    		
    	case "Month":
    		for(Task task : getSavedTasksForThisUser()) {
				Date taskDate = task.getDate();
				// If date is correct
				if(taskDate.getYear() == userDate.getYear() && taskDate.getMonth() == userDate.getMonth()) 
				{
					if(currentTime.getTime() <= taskDate.getTime()) { // If in the future
						if(task.isIs_completed()) {
							completedTasks++;
						}
					} else if (currentTime.getTime() > taskDate.getTime()) { // If in the past
						if(task.isIs_completed()) {
							completedTasks++;
						} else {
							failedTasks++;
						}
					}
				}
			}
			
    		break;
    		
    	case "Year":
    		for(Task task : getSavedTasksForThisUser()) {
				Date taskDate = task.getDate();
				// If date is correct
				if(taskDate.getYear() == userDate.getYear()) 
				{
					if(currentTime.getTime() <= taskDate.getTime()) { // If in the future
						if(task.isIs_completed()) {
							completedTasks++;
						}
					} else if (currentTime.getTime() > taskDate.getTime()) { // If in the past
						if(task.isIs_completed()) {
							completedTasks++;
						} else {
							failedTasks++;
						}
					}
				}
			}
			
    		break;
    	default:
    		break;
    		
		}
		getMainContView().getCompletedTasksStatisticsPanelLable().setText(Integer.toString(completedTasks));
		getMainContView().getMissedTasksStatisticsPanelLable().setText(Integer.toString(failedTasks));
	}
}
