package client.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SpringLayout;
import javax.swing.UIManager;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import com.toedter.calendar.JCalendar;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import java.awt.List;
import javax.swing.DefaultComboBoxModel;

public class MainContentWindowView {

	private JFrame frame;
	private JPanel leftCardLayoutPanel;
	private MainContentWindowPresenter mainContWindowPres;
	
	//Buttons
	private JButton manageTasksWindowButton;
	private JButton exitButton;
	private JButton tasksForDayWindowButton;
	private JButton tasksForWeekWindowButton;
	private JButton repeatingTaskWindowButton;
	private JButton statisticWindowButton;
	private JButton addTaskTasksForTodayWindowButton;
	private JButton deleteTaskTasksForTodayWindowButton;
	private JButton changeIsFinishedPropertyToOppositeTasksForTodayWindowButton;
	private JButton manageTasksForMultipleDaysWindowAddButton;
	private JButton manageTasksForMultipleDaysWindowDeleteButton;
	private JButton chooseDifferentDayTasksForTodayWindowButton;
	//
	
	//Indicators
	private JPanel chooseDateConnectionIndicator;
	private JPanel tasksForTodayConnectionIndicator;
	private JPanel tasksForWeekConnectionIndicator;
	private JPanel repeatingTasksConnectionIndicator;
	private JPanel statisticsConnectionIndicator;
	//
	
	// Labels
	private JLabel chosenDayByUserTaskForDayWindowLable;
	private JLabel currentChosenDateByUserChooseDateWindowLabel;
	private JLabel todayIsTaskForWeekWindowLabel;
	private JLabel completedTasksStatisticsPanelLable;
	private JLabel missedTasksStatisticsPanelLable;
	//
	
	private JCalendar calendar;
	private JTable tableOfTasks;
	private JTable tasksForWeekPanelDaysOfTheWeek;
	private JTextField manageTasksForMultipleDaysWindowTitleTextField;
	private JComboBox chooseDayStatisticsPanelComboBox;
	
	private int userID;
	
	private JDateChooser manageTasksForMultipleDaysWindowFromDateChooser;
	private JDateChooser manageTasksForMultipleDaysWindowToDateChooser;
	private JSpinner manageTasksForMultipleDaysWindowTimeSpinner;
	
	/**
	 * Create the application.
	 */
	public MainContentWindowView(int userID) {
		this.setUserID(userID);
		initialize();
		frame.setVisible(true);
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JPanel getLeftCardLayoutPanel() {
		return leftCardLayoutPanel;
	}

	protected MainContentWindowPresenter getMainContWindowPres() {
		return mainContWindowPres;
	}

	protected void setMainContWindowPres(MainContentWindowPresenter mainContWindowPres) {
		this.mainContWindowPres = mainContWindowPres;
	}

	public JPanel getChosseDateConnectionIndicator() {
		return chooseDateConnectionIndicator;
	}

	public void setChooseDateConnectionIndicator(JPanel chooseDateConnectionIndicator) {
		this.chooseDateConnectionIndicator = chooseDateConnectionIndicator;
	}

	public JPanel getTasksForTodayConnectionIndicator() {
		return tasksForTodayConnectionIndicator;
	}

	public void setTasksForTodayConnectionIndicator(JPanel tasksForTodayConnectionIndicator) {
		this.tasksForTodayConnectionIndicator = tasksForTodayConnectionIndicator;
	}

	public JPanel getTasksForWeekConnectionIndicator() {
		return tasksForWeekConnectionIndicator;
	}

	public void setTasksForWeekConnectionIndicator(JPanel tasksForWeekConnectionIndicator) {
		this.tasksForWeekConnectionIndicator = tasksForWeekConnectionIndicator;
	}

	public JPanel getRepeatingTasksConnectionIndicator() {
		return repeatingTasksConnectionIndicator;
	}

	public void setRepeatingTasksConnectionIndicator(JPanel repeatingTasksConnectionIndicator) {
		this.repeatingTasksConnectionIndicator = repeatingTasksConnectionIndicator;
	}

	public JPanel getStatisticsConnectionIndicator() {
		return statisticsConnectionIndicator;
	}

	public void setStatisticsConnectionIndicator(JPanel statisticsConnectionIndicator) {
		this.statisticsConnectionIndicator = statisticsConnectionIndicator;
	}

	public JButton getManageTasksWindowButton() {
		return manageTasksWindowButton;
	}

	public void setManageTasksWindowButton(JButton manageTasksWindowButton) {
		this.manageTasksWindowButton = manageTasksWindowButton;
	}

	public JButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}

	public JButton getTasksForDayWindowButton() {
		return tasksForDayWindowButton;
	}

	public void setTasksForDayWindowButton(JButton tasksForDayWindowButton) {
		this.tasksForDayWindowButton = tasksForDayWindowButton;
	}

	public JButton getTasksForWeekWindowButton() {
		return tasksForWeekWindowButton;
	}

	public void setTasksForWeekWindowButton(JButton tasksForWeekWindowButton) {
		this.tasksForWeekWindowButton = tasksForWeekWindowButton;
	}

	public JButton getRepeatingTaskWindowButton() {
		return repeatingTaskWindowButton;
	}

	public void setRepeatingTaskWindowButton(JButton repeatingTaskWindowButton) {
		this.repeatingTaskWindowButton = repeatingTaskWindowButton;
	}

	public JButton getStatisticWindowButton() {
		return statisticWindowButton;
	}

	public void setStatisticWindowButton(JButton statisticWindowButton) {
		this.statisticWindowButton = statisticWindowButton;
	}

	public JButton getAddTaskTasksForTodayWindowButton() {
		return addTaskTasksForTodayWindowButton;
	}

	public void setAddTaskTasksForTodayWindowButton(JButton addTaskTasksForTodayWindowButton) {
		this.addTaskTasksForTodayWindowButton = addTaskTasksForTodayWindowButton;
	}

	public JCalendar getCalendar() {
		return calendar;
	}

	public void setCalendar(JCalendar calendar) {
		this.calendar = calendar;
	}

	public JLabel getChosenDayByUserTaskForDayWindowLable() {
		return chosenDayByUserTaskForDayWindowLable;
	}

	public void setChosenDayByUserTaskForDayWindowLable(JLabel chosenDayByUserLable) {
		this.chosenDayByUserTaskForDayWindowLable = chosenDayByUserLable;
	}

	public JLabel getCurrentChosenDateByUserChooseDateWindowLabel() {
		return currentChosenDateByUserChooseDateWindowLabel;
	}

	public void setCurrentChosenDateByUserChooseDateWindowLabel(JLabel currentChosenDateByUserChooseDateWindowLabel) {
		this.currentChosenDateByUserChooseDateWindowLabel = currentChosenDateByUserChooseDateWindowLabel;
	}

	public JLabel getTodayIsTaskForWeekWindowLabel() {
		return todayIsTaskForWeekWindowLabel;
	}

	public void setTodayIsTaskForWeekWindowLabel(JLabel todayIsTaskForWeekWindowLabel) {
		this.todayIsTaskForWeekWindowLabel = todayIsTaskForWeekWindowLabel;
	}

	public JButton getDeleteTaskTasksForTodayWindowButton() {
		return deleteTaskTasksForTodayWindowButton;
	}

	public void setDeleteTaskTasksForTodayWindowButton(JButton deleteTaskTasksForTodayWindowButton) {
		this.deleteTaskTasksForTodayWindowButton = deleteTaskTasksForTodayWindowButton;
	}

	public JTable getTableOfTasks() {
		return tableOfTasks;
	}

	public void setTableOfTasks(JTable tableOfTasks) {
		this.tableOfTasks = tableOfTasks;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public JButton getChangeIsFinishedPropertyToOppositeTasksForTodayWindowButton() {
		return changeIsFinishedPropertyToOppositeTasksForTodayWindowButton;
	}

	public void setChangeIsFinishedPropertyToOppositeTasksForTodayWindowButton(
			JButton changeIsFinishedPropertyToOppositeTasksForTodayWindowButton) {
		this.changeIsFinishedPropertyToOppositeTasksForTodayWindowButton = changeIsFinishedPropertyToOppositeTasksForTodayWindowButton;
	}

	public JComboBox getChooseDayStatisticsPanelComboBox() {
		return chooseDayStatisticsPanelComboBox;
	}

	public void setChooseDayStatisticsPanelComboBox(JComboBox chooseDayStatisticsPanelComboBox) {
		this.chooseDayStatisticsPanelComboBox = chooseDayStatisticsPanelComboBox;
	}

	public JLabel getCompletedTasksStatisticsPanelLable() {
		return completedTasksStatisticsPanelLable;
	}

	public void setCompletedTasksStatisticsPanelLable(JLabel completedTasksStatisticsPanelLable) {
		this.completedTasksStatisticsPanelLable = completedTasksStatisticsPanelLable;
	}

	public JLabel getMissedTasksStatisticsPanelLable() {
		return missedTasksStatisticsPanelLable;
	}

	public void setMissedTasksStatisticsPanelLable(JLabel missedTasksStatisticsPanelLable) {
		this.missedTasksStatisticsPanelLable = missedTasksStatisticsPanelLable;
	}

	public JTable getTasksForWeekPanelDaysOfTheWeek() {
		return tasksForWeekPanelDaysOfTheWeek;
	}

	public void setTasksForWeekPanelDaysOfTheWeek(JTable tasksForWeekPanelDaysOfTheWeek) {
		this.tasksForWeekPanelDaysOfTheWeek = tasksForWeekPanelDaysOfTheWeek;
	}

	public JButton getManageTasksForMultipleDaysWindowAddButton() {
		return manageTasksForMultipleDaysWindowAddButton;
	}

	public void setManageTasksForMultipleDaysWindowAddButton(JButton manageTasksForMultipleDaysWindowAddButton) {
		this.manageTasksForMultipleDaysWindowAddButton = manageTasksForMultipleDaysWindowAddButton;
	}

	public JButton getManageTasksForMultipleDaysWindowDeleteButton() {
		return manageTasksForMultipleDaysWindowDeleteButton;
	}

	public void setManageTasksForMultipleDaysWindowDeleteButton(JButton manageTasksForMultipleDaysWindowDeleteButton) {
		this.manageTasksForMultipleDaysWindowDeleteButton = manageTasksForMultipleDaysWindowDeleteButton;
	}

	public JTextField getManageTasksForMultipleDaysWindowTitleTextField() {
		return manageTasksForMultipleDaysWindowTitleTextField;
	}

	public void setManageTasksForMultipleDaysWindowTitleTextField(
			JTextField manageTasksForMultipleDaysWindowTitleTextField) {
		this.manageTasksForMultipleDaysWindowTitleTextField = manageTasksForMultipleDaysWindowTitleTextField;
		manageTasksForMultipleDaysWindowTitleTextField.setText("Your task goes here");
	}

	public JDateChooser getManageTasksForMultipleDaysWindowFromDateChooser() {
		return manageTasksForMultipleDaysWindowFromDateChooser;
	}

	public void setManageTasksForMultipleDaysWindowFromDateChooser(
			JDateChooser manageTasksForMultipleDaysWindowFromDateChooser) {
		this.manageTasksForMultipleDaysWindowFromDateChooser = manageTasksForMultipleDaysWindowFromDateChooser;
	}

	public JDateChooser getManageTasksForMultipleDaysWindowToDateChooser() {
		return manageTasksForMultipleDaysWindowToDateChooser;
	}

	public void setManageTasksForMultipleDaysWindowToDateChooser(
			JDateChooser manageTasksForMultipleDaysWindowToDateChooser) {
		this.manageTasksForMultipleDaysWindowToDateChooser = manageTasksForMultipleDaysWindowToDateChooser;
	}

	public JSpinner getManageTasksForMultipleDaysWindowTimeSpinner() {
		return manageTasksForMultipleDaysWindowTimeSpinner;
	}

	public void setManageTasksForMultipleDaysWindowTimeSpinner(JSpinner manageTasksForMultipleDaysWindowTimeSpinner) {
		this.manageTasksForMultipleDaysWindowTimeSpinner = manageTasksForMultipleDaysWindowTimeSpinner;
	}

	public JButton getChooseDifferentDayTasksForTodayWindowButton() {
		return chooseDifferentDayTasksForTodayWindowButton;
	}

	public void setChooseDifferentDayTasksForTodayWindowButton(JButton chooseDifferentDayTasksForTodayWindowButton) {
		this.chooseDifferentDayTasksForTodayWindowButton = chooseDifferentDayTasksForTodayWindowButton;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		SpringLayout springLayout = new SpringLayout();
		frame.getContentPane().setLayout(springLayout);
		
		JPanel modePanel = new JPanel();
		modePanel.setBackground(new Color(128, 128, 128));
		springLayout.putConstraint(SpringLayout.NORTH, modePanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, modePanel, 0, SpringLayout.WEST, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.SOUTH, modePanel, 543, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.EAST, modePanel, 120, SpringLayout.WEST, frame.getContentPane());
		frame.getContentPane().add(modePanel);
		
		leftCardLayoutPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.EAST, leftCardLayoutPanel, 0, SpringLayout.EAST, frame.getContentPane());
		leftCardLayoutPanel.setBackground(new Color(64, 139, 64));
		springLayout.putConstraint(SpringLayout.NORTH, leftCardLayoutPanel, 0, SpringLayout.NORTH, frame.getContentPane());
		springLayout.putConstraint(SpringLayout.WEST, leftCardLayoutPanel, 0, SpringLayout.EAST, modePanel);
		SpringLayout sl_modePanel = new SpringLayout();
		modePanel.setLayout(sl_modePanel);
		
		this.setManageTasksWindowButton(new JButton(""));
		sl_modePanel.putConstraint(SpringLayout.NORTH, manageTasksWindowButton, 10, SpringLayout.NORTH, modePanel);
		sl_modePanel.putConstraint(SpringLayout.WEST, manageTasksWindowButton, 20, SpringLayout.WEST, modePanel);
		sl_modePanel.putConstraint(SpringLayout.SOUTH, manageTasksWindowButton, 90, SpringLayout.NORTH, modePanel);
		sl_modePanel.putConstraint(SpringLayout.EAST, manageTasksWindowButton, -20, SpringLayout.EAST, modePanel);
		manageTasksWindowButton.setIcon(new ImageIcon(MainContentWindowView.class.getResource("/client/resources/Calendar.png")));
		manageTasksWindowButton.setForeground(new Color(64, 139, 64));
		manageTasksWindowButton.setBackground(new Color(64, 139, 64));
		modePanel.add(manageTasksWindowButton);
		
		this.setExitButton(new JButton(""));
		exitButton.setIcon(new ImageIcon(MainContentWindowView.class.getResource("/client/resources/Exit.png")));
		sl_modePanel.putConstraint(SpringLayout.NORTH, exitButton, -90, SpringLayout.SOUTH, modePanel);
		sl_modePanel.putConstraint(SpringLayout.WEST, exitButton, 20, SpringLayout.WEST, modePanel);
		sl_modePanel.putConstraint(SpringLayout.SOUTH, exitButton, -10, SpringLayout.SOUTH, modePanel);
		sl_modePanel.putConstraint(SpringLayout.EAST, exitButton, -20, SpringLayout.EAST, modePanel);
		exitButton.setForeground(new Color(64, 139, 64));
		exitButton.setBackground(new Color(64, 139, 64));
		modePanel.add(exitButton);
		
		this.setTasksForDayWindowButton(new JButton(""));
		tasksForDayWindowButton.setIcon(new ImageIcon(MainContentWindowView.class.getResource("/client/resources/Day.png")));
		sl_modePanel.putConstraint(SpringLayout.NORTH, tasksForDayWindowButton, 6, SpringLayout.SOUTH, manageTasksWindowButton);
		sl_modePanel.putConstraint(SpringLayout.WEST, tasksForDayWindowButton, 20, SpringLayout.WEST, modePanel);
		sl_modePanel.putConstraint(SpringLayout.SOUTH, tasksForDayWindowButton, 86, SpringLayout.SOUTH, manageTasksWindowButton);
		sl_modePanel.putConstraint(SpringLayout.EAST, tasksForDayWindowButton, 100, SpringLayout.WEST, modePanel);
		tasksForDayWindowButton.setForeground(new Color(64, 139, 64));
		tasksForDayWindowButton.setBackground(new Color(64, 139, 64));
		modePanel.add(tasksForDayWindowButton);
		
		this.setTasksForWeekWindowButton(new JButton(""));
		tasksForWeekWindowButton.setIcon(new ImageIcon(MainContentWindowView.class.getResource("/client/resources/Week.png")));
		sl_modePanel.putConstraint(SpringLayout.NORTH, tasksForWeekWindowButton, 6, SpringLayout.SOUTH, tasksForDayWindowButton);
		sl_modePanel.putConstraint(SpringLayout.WEST, tasksForWeekWindowButton, 20, SpringLayout.WEST, modePanel);
		sl_modePanel.putConstraint(SpringLayout.SOUTH, tasksForWeekWindowButton, -281, SpringLayout.SOUTH, modePanel);
		sl_modePanel.putConstraint(SpringLayout.EAST, tasksForWeekWindowButton, 100, SpringLayout.WEST, modePanel);
		tasksForWeekWindowButton.setForeground(new Color(64, 139, 64));
		tasksForWeekWindowButton.setBackground(new Color(64, 139, 64));
		modePanel.add(tasksForWeekWindowButton);
		
		this.setRepeatingTaskWindowButton(new JButton(""));
		repeatingTaskWindowButton.setIcon(new ImageIcon(MainContentWindowView.class.getResource("/client/resources/Redo.png")));
		sl_modePanel.putConstraint(SpringLayout.NORTH, repeatingTaskWindowButton, 6, SpringLayout.SOUTH, tasksForWeekWindowButton);
		sl_modePanel.putConstraint(SpringLayout.WEST, repeatingTaskWindowButton, 21, SpringLayout.WEST, modePanel);
		sl_modePanel.putConstraint(SpringLayout.SOUTH, repeatingTaskWindowButton, -195, SpringLayout.SOUTH, modePanel);
		sl_modePanel.putConstraint(SpringLayout.EAST, repeatingTaskWindowButton, -19, SpringLayout.EAST, modePanel);
		repeatingTaskWindowButton.setForeground(new Color(64, 139, 64));
		repeatingTaskWindowButton.setBackground(new Color(64, 139, 64));
		modePanel.add(repeatingTaskWindowButton);
		
		this.setStatisticWindowButton(new JButton(""));
		statisticWindowButton.setIcon(new ImageIcon(MainContentWindowView.class.getResource("/client/resources/Results.png")));
		sl_modePanel.putConstraint(SpringLayout.NORTH, statisticWindowButton, 6, SpringLayout.SOUTH, repeatingTaskWindowButton);
		sl_modePanel.putConstraint(SpringLayout.WEST, statisticWindowButton, 21, SpringLayout.WEST, modePanel);
		sl_modePanel.putConstraint(SpringLayout.SOUTH, statisticWindowButton, -19, SpringLayout.NORTH, exitButton);
		sl_modePanel.putConstraint(SpringLayout.EAST, statisticWindowButton, -19, SpringLayout.EAST, modePanel);
		statisticWindowButton.setForeground(new Color(64, 139, 64));
		statisticWindowButton.setBackground(new Color(64, 139, 64));
		modePanel.add(statisticWindowButton);
		springLayout.putConstraint(SpringLayout.SOUTH, leftCardLayoutPanel, 543, SpringLayout.NORTH, frame.getContentPane());
		frame.getContentPane().add(leftCardLayoutPanel);
		leftCardLayoutPanel.setLayout(new CardLayout(0, 0));
		
		JPanel chooseDatePanel = new JPanel();
		chooseDatePanel.setBackground(new Color(64, 139, 64));
		leftCardLayoutPanel.add(chooseDatePanel, "ManageTasksWindow");
		SpringLayout sl_chooseDatePanel = new SpringLayout();
		chooseDatePanel.setLayout(sl_chooseDatePanel);
		
		this.setChooseDateConnectionIndicator(new JPanel());
		chooseDateConnectionIndicator.setBackground(Color.RED);
		sl_chooseDatePanel.putConstraint(SpringLayout.NORTH, chooseDateConnectionIndicator, -40, SpringLayout.SOUTH, chooseDatePanel);
		sl_chooseDatePanel.putConstraint(SpringLayout.WEST, chooseDateConnectionIndicator, -40, SpringLayout.EAST, chooseDatePanel);
		sl_chooseDatePanel.putConstraint(SpringLayout.SOUTH, chooseDateConnectionIndicator, -10, SpringLayout.SOUTH, chooseDatePanel);
		sl_chooseDatePanel.putConstraint(SpringLayout.EAST, chooseDateConnectionIndicator, -10, SpringLayout.EAST, chooseDatePanel);
		chooseDatePanel.add(chooseDateConnectionIndicator);
		
		this.setCalendar(new JCalendar());
		sl_chooseDatePanel.putConstraint(SpringLayout.SOUTH, calendar, -61, SpringLayout.SOUTH, chooseDatePanel);
		calendar.setWeekOfYearVisible(false);
		calendar.setTodayButtonText("Today");
		sl_chooseDatePanel.putConstraint(SpringLayout.WEST, calendar, 10, SpringLayout.WEST, chooseDatePanel);
		sl_chooseDatePanel.putConstraint(SpringLayout.EAST, calendar, 0, SpringLayout.EAST, chooseDateConnectionIndicator);
		calendar.getDayChooser().setAlwaysFireDayProperty(true);
		calendar.setDecorationBordersVisible(true);
		calendar.getDayChooser().getDayPanel().setBackground(new Color(204, 245, 179));
		calendar.setBackground(new Color(64, 139, 64));
		calendar.setLocale(Locale.ENGLISH);
		calendar.setDoubleBuffered(true);
		calendar.setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		
		calendar.getMonthChooser().setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		calendar.getYearChooser().setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		calendar.getDayChooser().setFont(new Font("Comic Sans MS", Font.BOLD, 25));
		chooseDatePanel.add(calendar);
		
		JLabel ChooseDateToManageGoalLable = new JLabel("Choose a date to manage your goals");
		sl_chooseDatePanel.putConstraint(SpringLayout.NORTH, calendar, 21, SpringLayout.SOUTH, ChooseDateToManageGoalLable);
		sl_chooseDatePanel.putConstraint(SpringLayout.NORTH, ChooseDateToManageGoalLable, 15, SpringLayout.NORTH, chooseDatePanel);
		sl_chooseDatePanel.putConstraint(SpringLayout.WEST, ChooseDateToManageGoalLable, 10, SpringLayout.WEST, chooseDatePanel);
		sl_chooseDatePanel.putConstraint(SpringLayout.EAST, ChooseDateToManageGoalLable, -10, SpringLayout.EAST, chooseDatePanel);
		ChooseDateToManageGoalLable.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
		chooseDatePanel.add(ChooseDateToManageGoalLable);
		
		JLabel currentChosenDateChooseDateWindowLabel = new JLabel("Current chosen date:");
		sl_chooseDatePanel.putConstraint(SpringLayout.NORTH, currentChosenDateChooseDateWindowLabel, 16, SpringLayout.SOUTH, calendar);
		sl_chooseDatePanel.putConstraint(SpringLayout.EAST, currentChosenDateChooseDateWindowLabel, -460, SpringLayout.EAST, chooseDatePanel);
		currentChosenDateChooseDateWindowLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		chooseDatePanel.add(currentChosenDateChooseDateWindowLabel);
		
		this.setCurrentChosenDateByUserChooseDateWindowLabel(new JLabel("the 7th of July 2023"));
		sl_chooseDatePanel.putConstraint(SpringLayout.NORTH, currentChosenDateByUserChooseDateWindowLabel, 0, SpringLayout.NORTH, currentChosenDateChooseDateWindowLabel);
		sl_chooseDatePanel.putConstraint(SpringLayout.WEST, currentChosenDateByUserChooseDateWindowLabel, 6, SpringLayout.EAST, currentChosenDateChooseDateWindowLabel);
		currentChosenDateByUserChooseDateWindowLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		chooseDatePanel.add(currentChosenDateByUserChooseDateWindowLabel);
		
		JPanel tasksForTodayPanel = new JPanel();
		tasksForTodayPanel.setBackground(new Color(64, 139, 64));
		leftCardLayoutPanel.add(tasksForTodayPanel, "TasksForDayWindow");
		SpringLayout sl_tasksForTodayPanel = new SpringLayout();
		tasksForTodayPanel.setLayout(sl_tasksForTodayPanel);
		
		JLabel theChosenDayLable = new JLabel("The chosen day is ");
		theChosenDayLable.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, theChosenDayLable, 10, SpringLayout.NORTH, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, theChosenDayLable, 10, SpringLayout.WEST, tasksForTodayPanel);
		tasksForTodayPanel.add(theChosenDayLable);
		
		this.setTasksForTodayConnectionIndicator(new JPanel());
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, tasksForTodayConnectionIndicator, -40, SpringLayout.SOUTH, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, tasksForTodayConnectionIndicator, -40, SpringLayout.EAST, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, tasksForTodayConnectionIndicator, -10, SpringLayout.SOUTH, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.EAST, tasksForTodayConnectionIndicator, -10, SpringLayout.EAST, tasksForTodayPanel);
		tasksForTodayConnectionIndicator.setBackground(Color.RED);
		tasksForTodayPanel.add(tasksForTodayConnectionIndicator);

		this.setChosenDayByUserTaskForDayWindowLable(new JLabel("the 7th of July 2023"));
		chosenDayByUserTaskForDayWindowLable.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, chosenDayByUserTaskForDayWindowLable, 6, SpringLayout.SOUTH, theChosenDayLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, chosenDayByUserTaskForDayWindowLable, 0, SpringLayout.WEST, theChosenDayLable);
		tasksForTodayPanel.add(chosenDayByUserTaskForDayWindowLable);
		
		JLabel youHaveNextTasksForTodayLable = new JLabel("You have next tasks for today");
		youHaveNextTasksForTodayLable.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, youHaveNextTasksForTodayLable, 0, SpringLayout.WEST, theChosenDayLable);
		tasksForTodayPanel.add(youHaveNextTasksForTodayLable);
		
		this.setAddTaskTasksForTodayWindowButton(new JButton("Add task"));
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, addTaskTasksForTodayWindowButton, 0, SpringLayout.NORTH, theChosenDayLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, addTaskTasksForTodayWindowButton, 122, SpringLayout.EAST, chosenDayByUserTaskForDayWindowLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, addTaskTasksForTodayWindowButton, -488, SpringLayout.SOUTH, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.EAST, addTaskTasksForTodayWindowButton, -10, SpringLayout.EAST, tasksForTodayPanel);
		addTaskTasksForTodayWindowButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tasksForTodayPanel.add(addTaskTasksForTodayWindowButton);
		
		this.setDeleteTaskTasksForTodayWindowButton(new JButton("Delete task"));
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, deleteTaskTasksForTodayWindowButton, 0, SpringLayout.WEST, addTaskTasksForTodayWindowButton);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.EAST, deleteTaskTasksForTodayWindowButton, -10, SpringLayout.EAST, tasksForTodayPanel);
		deleteTaskTasksForTodayWindowButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tasksForTodayPanel.add(deleteTaskTasksForTodayWindowButton);
		
		this.setChooseDifferentDayTasksForTodayWindowButton(new JButton("Go back today"));
		chooseDifferentDayTasksForTodayWindowButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, chooseDifferentDayTasksForTodayWindowButton, 0, SpringLayout.NORTH, tasksForTodayConnectionIndicator);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, chooseDifferentDayTasksForTodayWindowButton, 248, SpringLayout.WEST, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, chooseDifferentDayTasksForTodayWindowButton, 0, SpringLayout.SOUTH, tasksForTodayConnectionIndicator);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.EAST, chooseDifferentDayTasksForTodayWindowButton, -219, SpringLayout.WEST, tasksForTodayConnectionIndicator);
		chooseDifferentDayTasksForTodayWindowButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
		tasksForTodayPanel.add(chooseDifferentDayTasksForTodayWindowButton);
		
		tableOfTasks = new JTable();
		tableOfTasks.setSurrendersFocusOnKeystroke(true);
		tableOfTasks.setFillsViewportHeight(true);
		tableOfTasks.addColumn(new TableColumn ());
		DefaultTableModel tableModel = new DefaultTableModel(
				new Object[][] {
				},
				new String[] {"No tasks"}	
				);
		tableOfTasks.setModel(tableModel);
		tableOfTasks.getTableHeader().setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		tableOfTasks.setRowHeight(30);
		tableOfTasks.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
		JScrollPane scrollPane = new JScrollPane(tableOfTasks);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, deleteTaskTasksForTodayWindowButton, -82, SpringLayout.NORTH, scrollPane);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, scrollPane, 192, SpringLayout.NORTH, tasksForTodayPanel);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, scrollPane, -6, SpringLayout.NORTH, tasksForTodayConnectionIndicator);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, youHaveNextTasksForTodayLable, -6, SpringLayout.NORTH, scrollPane);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, scrollPane, 0, SpringLayout.WEST, theChosenDayLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.EAST, scrollPane, 0, SpringLayout.EAST, tasksForTodayConnectionIndicator);
		tasksForTodayPanel.add(scrollPane);

		JLabel theTaskChosenLable = new JLabel("The task chosen is ");
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, deleteTaskTasksForTodayWindowButton, -13, SpringLayout.NORTH, theTaskChosenLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, theTaskChosenLable, 6, SpringLayout.SOUTH, chosenDayByUserTaskForDayWindowLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, theTaskChosenLable, 0, SpringLayout.WEST, theChosenDayLable);
		theTaskChosenLable.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
		tasksForTodayPanel.add(theTaskChosenLable);
		
		JLabel selectedTaskLable = new JLabel("no task is chosen select one");
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, selectedTaskLable, 6, SpringLayout.SOUTH, theTaskChosenLable);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, selectedTaskLable, 0, SpringLayout.WEST, theChosenDayLable);
		selectedTaskLable.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		tasksForTodayPanel.add(selectedTaskLable);
		
		this.setChangeIsFinishedPropertyToOppositeTasksForTodayWindowButton(new JButton("Opposite finished"));
		sl_tasksForTodayPanel.putConstraint(SpringLayout.NORTH, changeIsFinishedPropertyToOppositeTasksForTodayWindowButton, 9, SpringLayout.SOUTH, deleteTaskTasksForTodayWindowButton);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.WEST, changeIsFinishedPropertyToOppositeTasksForTodayWindowButton, 0, SpringLayout.WEST, addTaskTasksForTodayWindowButton);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.SOUTH, changeIsFinishedPropertyToOppositeTasksForTodayWindowButton, 54, SpringLayout.SOUTH, deleteTaskTasksForTodayWindowButton);
		sl_tasksForTodayPanel.putConstraint(SpringLayout.EAST, changeIsFinishedPropertyToOppositeTasksForTodayWindowButton, 0, SpringLayout.EAST, tasksForTodayConnectionIndicator);
		changeIsFinishedPropertyToOppositeTasksForTodayWindowButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tasksForTodayPanel.add(changeIsFinishedPropertyToOppositeTasksForTodayWindowButton);
		
		JPanel tasksForWeekPanel = new JPanel();
		tasksForWeekPanel.setBackground(new Color(64, 139, 64));
		leftCardLayoutPanel.add(tasksForWeekPanel, "TasksForWeekWindow");
		SpringLayout sl_tasksForWeekPanel = new SpringLayout();
		tasksForWeekPanel.setLayout(sl_tasksForWeekPanel);
		
		this.setTasksForWeekConnectionIndicator(new JPanel());
		sl_tasksForWeekPanel.putConstraint(SpringLayout.NORTH, tasksForWeekConnectionIndicator, -40, SpringLayout.SOUTH, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.WEST, tasksForWeekConnectionIndicator, -40, SpringLayout.EAST, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.SOUTH, tasksForWeekConnectionIndicator, -10, SpringLayout.SOUTH, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.EAST, tasksForWeekConnectionIndicator, -10, SpringLayout.EAST, tasksForWeekPanel);
		tasksForWeekConnectionIndicator.setBackground(Color.RED);
		tasksForWeekPanel.add(tasksForWeekConnectionIndicator);
		
		this.setTodayIsTaskForWeekWindowLabel(new JLabel("Today is the 10th of June 2024"));
		sl_tasksForWeekPanel.putConstraint(SpringLayout.NORTH, todayIsTaskForWeekWindowLabel, 10, SpringLayout.NORTH, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.WEST, todayIsTaskForWeekWindowLabel, 10, SpringLayout.WEST, tasksForWeekPanel);
		todayIsTaskForWeekWindowLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tasksForWeekPanel.add(todayIsTaskForWeekWindowLabel);
		
		tasksForWeekPanelDaysOfTheWeek = new JTable();
		tasksForWeekPanelDaysOfTheWeek.setFillsViewportHeight(true);
		tasksForWeekPanelDaysOfTheWeek.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"
			}
		));
		tasksForWeekPanelDaysOfTheWeek.getTableHeader().setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		tasksForWeekPanelDaysOfTheWeek.setRowHeight(30);
		tasksForWeekPanelDaysOfTheWeek.setRowMargin(10);
		JScrollPane scrollPane2 = new JScrollPane(tasksForWeekPanelDaysOfTheWeek);
		tasksForWeekPanelDaysOfTheWeek.setFont(new Font("Comic Sans MS", Font.ITALIC, 14));
		sl_tasksForWeekPanel.putConstraint(SpringLayout.NORTH, scrollPane2, -321, SpringLayout.SOUTH, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.WEST, scrollPane2, 10, SpringLayout.WEST, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.SOUTH, scrollPane2, -6, SpringLayout.NORTH, tasksForWeekConnectionIndicator);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.EAST, scrollPane2, 0, SpringLayout.EAST, tasksForWeekConnectionIndicator);
		tasksForWeekPanel.add(scrollPane2);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Click one day of the week to see and manage\r\n");
		sl_tasksForWeekPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_1, 0, SpringLayout.WEST, todayIsTaskForWeekWindowLabel);
		lblNewLabel_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tasksForWeekPanel.add(lblNewLabel_1_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("its tasks");
		sl_tasksForWeekPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_1_1, 147, SpringLayout.NORTH, tasksForWeekPanel);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_1_1_1, -6, SpringLayout.NORTH, lblNewLabel_1_1_1_1_1);
		sl_tasksForWeekPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_1_1, 0, SpringLayout.WEST, todayIsTaskForWeekWindowLabel);
		lblNewLabel_1_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		tasksForWeekPanel.add(lblNewLabel_1_1_1_1_1);
		
		JPanel repeatingTasksPanel = new JPanel();
		repeatingTasksPanel.setBackground(new Color(64, 139, 64));
		leftCardLayoutPanel.add(repeatingTasksPanel, "RepeatingTasksWindow");
		SpringLayout sl_repeatingTasksPanel = new SpringLayout();
		repeatingTasksPanel.setLayout(sl_repeatingTasksPanel);
		
		this.setRepeatingTasksConnectionIndicator(new JPanel());
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, repeatingTasksConnectionIndicator, -40, SpringLayout.SOUTH, repeatingTasksPanel);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, repeatingTasksConnectionIndicator, -40, SpringLayout.EAST, repeatingTasksPanel);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, repeatingTasksConnectionIndicator, -10, SpringLayout.SOUTH, repeatingTasksPanel);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, repeatingTasksConnectionIndicator, -10, SpringLayout.EAST, repeatingTasksPanel);
		repeatingTasksConnectionIndicator.setBackground(Color.RED);
		repeatingTasksPanel.add(repeatingTasksConnectionIndicator);
		
		JLabel lblNewLabel_1_1_1_2 = new JLabel("Manage task for multiple days");
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2, 10, SpringLayout.NORTH, repeatingTasksPanel);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2, 10, SpringLayout.WEST, repeatingTasksPanel);
		lblNewLabel_1_1_1_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		repeatingTasksPanel.add(lblNewLabel_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1 = new JLabel("From:");
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1, 34, SpringLayout.SOUTH, lblNewLabel_1_1_1_2);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2);
		lblNewLabel_1_1_1_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		repeatingTasksPanel.add(lblNewLabel_1_1_1_2_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1 = new JLabel("To:");
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1, 39, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2);
		lblNewLabel_1_1_1_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		repeatingTasksPanel.add(lblNewLabel_1_1_1_2_1_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1 = new JLabel("Time:");
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1, 40, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2);
		lblNewLabel_1_1_1_2_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		repeatingTasksPanel.add(lblNewLabel_1_1_1_2_1_1_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1 = new JLabel("Title:");
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1, 10, SpringLayout.WEST, repeatingTasksPanel);
		lblNewLabel_1_1_1_2_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		repeatingTasksPanel.add(lblNewLabel_1_1_1_2_1_1_1_1);
		
		this.setManageTasksForMultipleDaysWindowDeleteButton(new JButton("Delete"));
		manageTasksForMultipleDaysWindowDeleteButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1, -49, SpringLayout.NORTH, manageTasksForMultipleDaysWindowDeleteButton);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, manageTasksForMultipleDaysWindowDeleteButton, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, manageTasksForMultipleDaysWindowDeleteButton, -55, SpringLayout.SOUTH, repeatingTasksPanel);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, manageTasksForMultipleDaysWindowDeleteButton, -357, SpringLayout.EAST, repeatingTasksPanel);
		repeatingTasksPanel.add(manageTasksForMultipleDaysWindowDeleteButton);
		
		this.setManageTasksForMultipleDaysWindowAddButton(new JButton("Add"));
		manageTasksForMultipleDaysWindowAddButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, manageTasksForMultipleDaysWindowAddButton, 379, SpringLayout.SOUTH, lblNewLabel_1_1_1_2);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, manageTasksForMultipleDaysWindowAddButton, 48, SpringLayout.EAST, manageTasksForMultipleDaysWindowDeleteButton);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, manageTasksForMultipleDaysWindowAddButton, -15, SpringLayout.NORTH, repeatingTasksConnectionIndicator);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, manageTasksForMultipleDaysWindowAddButton, -10, SpringLayout.EAST, repeatingTasksPanel);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, manageTasksForMultipleDaysWindowDeleteButton, 0, SpringLayout.NORTH, manageTasksForMultipleDaysWindowAddButton);
		repeatingTasksPanel.add(manageTasksForMultipleDaysWindowAddButton);
		
		this.setManageTasksForMultipleDaysWindowTimeSpinner(new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY)));
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, manageTasksForMultipleDaysWindowTimeSpinner, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, manageTasksForMultipleDaysWindowTimeSpinner, 8, SpringLayout.EAST, lblNewLabel_1_1_1_2_1_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, manageTasksForMultipleDaysWindowTimeSpinner, 0, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, manageTasksForMultipleDaysWindowTimeSpinner, -338, SpringLayout.EAST, repeatingTasksPanel);
		manageTasksForMultipleDaysWindowTimeSpinner.setLocale(Locale.ENGLISH);
		manageTasksForMultipleDaysWindowTimeSpinner.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		manageTasksForMultipleDaysWindowTimeSpinner.setEditor(new JSpinner.DateEditor(manageTasksForMultipleDaysWindowTimeSpinner, "HH:mm:ss"));
		repeatingTasksPanel.add(manageTasksForMultipleDaysWindowTimeSpinner);
		
		this.setManageTasksForMultipleDaysWindowFromDateChooser(new JDateChooser());
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, manageTasksForMultipleDaysWindowFromDateChooser, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, manageTasksForMultipleDaysWindowFromDateChooser, 6, SpringLayout.EAST, lblNewLabel_1_1_1_2_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, manageTasksForMultipleDaysWindowFromDateChooser, 0, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, manageTasksForMultipleDaysWindowFromDateChooser, -338, SpringLayout.EAST, repeatingTasksPanel);
		manageTasksForMultipleDaysWindowFromDateChooser.setLocale(Locale.ENGLISH);
		manageTasksForMultipleDaysWindowFromDateChooser.setDate(new Date());
		manageTasksForMultipleDaysWindowFromDateChooser.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		repeatingTasksPanel.add(manageTasksForMultipleDaysWindowFromDateChooser);
		
		this.setManageTasksForMultipleDaysWindowToDateChooser(new JDateChooser());
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, manageTasksForMultipleDaysWindowToDateChooser, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, manageTasksForMultipleDaysWindowToDateChooser, 6, SpringLayout.EAST, lblNewLabel_1_1_1_2_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, manageTasksForMultipleDaysWindowToDateChooser, 0, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, manageTasksForMultipleDaysWindowToDateChooser, -373, SpringLayout.EAST, repeatingTasksPanel);
		manageTasksForMultipleDaysWindowToDateChooser.setLocale(Locale.ENGLISH);
		manageTasksForMultipleDaysWindowToDateChooser.setDate(new Date());
		manageTasksForMultipleDaysWindowToDateChooser.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		repeatingTasksPanel.add(manageTasksForMultipleDaysWindowToDateChooser);
		
		this.setManageTasksForMultipleDaysWindowTitleTextField(new JTextField());
		sl_repeatingTasksPanel.putConstraint(SpringLayout.NORTH, manageTasksForMultipleDaysWindowTitleTextField, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.WEST, manageTasksForMultipleDaysWindowTitleTextField, 6, SpringLayout.EAST, lblNewLabel_1_1_1_2_1_1_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.SOUTH, manageTasksForMultipleDaysWindowTitleTextField, 0, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1);
		sl_repeatingTasksPanel.putConstraint(SpringLayout.EAST, manageTasksForMultipleDaysWindowTitleTextField, 0, SpringLayout.EAST, manageTasksForMultipleDaysWindowTimeSpinner);
		repeatingTasksPanel.add(manageTasksForMultipleDaysWindowTitleTextField);
		manageTasksForMultipleDaysWindowTitleTextField.setColumns(10);
		
		JPanel statisticsPanel = new JPanel();
		statisticsPanel.setBackground(new Color(64, 139, 64));
		leftCardLayoutPanel.add(statisticsPanel, "StatisticsWindow");
		SpringLayout sl_statisticsPanel = new SpringLayout();
		statisticsPanel.setLayout(sl_statisticsPanel);
		
		this.setStatisticsConnectionIndicator(new JPanel());
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, statisticsConnectionIndicator, -40, SpringLayout.SOUTH, statisticsPanel);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, statisticsConnectionIndicator, 626, SpringLayout.WEST, statisticsPanel);
		sl_statisticsPanel.putConstraint(SpringLayout.SOUTH, statisticsConnectionIndicator, -10, SpringLayout.SOUTH, statisticsPanel);
		sl_statisticsPanel.putConstraint(SpringLayout.EAST, statisticsConnectionIndicator, -10, SpringLayout.EAST, statisticsPanel);
		statisticsConnectionIndicator.setBackground(Color.RED);
		statisticsPanel.add(statisticsConnectionIndicator);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1 = new JLabel("Your statistics for the last");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1, 34, SpringLayout.NORTH, statisticsPanel);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1, 10, SpringLayout.WEST, statisticsPanel);
		lblNewLabel_1_1_1_2_1_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1);
		
		this.setChooseDayStatisticsPanelComboBox(new JComboBox());
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, chooseDayStatisticsPanelComboBox, -3, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, chooseDayStatisticsPanelComboBox, 6, SpringLayout.EAST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		chooseDayStatisticsPanelComboBox.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		chooseDayStatisticsPanelComboBox.setModel(new DefaultComboBoxModel(new String[] {"Day", "Week", "Month", "Year"}));
		statisticsPanel.add(chooseDayStatisticsPanelComboBox);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1 = new JLabel("You have completed");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1, 26, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1);
		
		this.setCompletedTasksStatisticsPanelLable(new JLabel("9999"));
		completedTasksStatisticsPanelLable.setForeground(Color.GREEN);
		completedTasksStatisticsPanelLable.setBackground(new Color(255, 255, 255));
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, completedTasksStatisticsPanelLable, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, completedTasksStatisticsPanelLable, 6, SpringLayout.EAST, lblNewLabel_1_1_1_2_1_1_1_1_1_1);
		completedTasksStatisticsPanelLable.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(completedTasksStatisticsPanelLable);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_2 = new JLabel("tasks");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_2, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_2, 6, SpringLayout.EAST, completedTasksStatisticsPanelLable);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_3 = new JLabel("You missed");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3, 25, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_3.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_3);
		
		this.setMissedTasksStatisticsPanelLable(new JLabel("9999"));
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, missedTasksStatisticsPanelLable, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, missedTasksStatisticsPanelLable, 6, SpringLayout.EAST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3);
		missedTasksStatisticsPanelLable.setForeground(new Color(255, 0, 0));
		missedTasksStatisticsPanelLable.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		missedTasksStatisticsPanelLable.setBackground(Color.WHITE);
		statisticsPanel.add(missedTasksStatisticsPanelLable);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_2_1 = new JLabel("tasks");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_2_1, 0, SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_2_1, 6, SpringLayout.EAST, missedTasksStatisticsPanelLable);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_2_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1 = new JLabel("You`re doing well, but there`s still room for ");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1, 19, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_1 = new JLabel("improvement");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_1, 16, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2 = new JLabel("You’re braver than you believe, ");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2, 23, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1 = new JLabel("stronger than you seem, and smarter");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1, 19, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1);
		
		JLabel lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1_1 = new JLabel("than you think.");
		sl_statisticsPanel.putConstraint(SpringLayout.NORTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1_1, 16, SpringLayout.SOUTH, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1);
		sl_statisticsPanel.putConstraint(SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1_1, 0, SpringLayout.WEST, lblNewLabel_1_1_1_2_1_1_1_1_1);
		lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1_1.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
		statisticsPanel.add(lblNewLabel_1_1_1_2_1_1_1_1_1_1_3_1_2_1_1);
		frame.setBounds(100, 100, 800, 580);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setMainContWindowPres(new MainContentWindowPresenter(this)); // Must be at the very end of initialize()
	}
}
