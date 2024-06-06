package client.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.SpringLayout;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import java.awt.Font;
import java.util.Locale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.components.JSpinField;

import client.manager.ThreadManager;

public class CreateNewTaskDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton newTaskDialogCancelButton;
	private JTextField newTaskDialogTitleTextField;
	private JButton newTaskDialogOkButton;
	private JSpinner newTaskDialogTimeSpinner;
	private JDateChooser newTaskDialogDateChooser;
	private MainContentWindowPresenter parentWindow;

	/**
	 * Create the dialog.
	 */
	public CreateNewTaskDialog(Date dateChosenByUser, MainContentWindowPresenter parentWindow) {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setParentWindow(parentWindow);
		setTitle("Add new task");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(64, 139, 64));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		SpringLayout sl_contentPanel = new SpringLayout();
		contentPanel.setLayout(sl_contentPanel);
		{
			newTaskDialogCancelButton = new JButton("Cancel");
			newTaskDialogCancelButton.addActionListener(new newTaskDialogCancelButtonListener(this));
			sl_contentPanel.putConstraint(SpringLayout.EAST, newTaskDialogCancelButton, 0, SpringLayout.EAST, contentPanel);
			contentPanel.add(newTaskDialogCancelButton);
			newTaskDialogCancelButton.setActionCommand("Cancel");
		}
		{
			newTaskDialogOkButton = new JButton("OK");
			sl_contentPanel.putConstraint(SpringLayout.NORTH, newTaskDialogCancelButton, 0, SpringLayout.NORTH, newTaskDialogOkButton);
			sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogCancelButton, 304, SpringLayout.WEST, newTaskDialogOkButton);
			sl_contentPanel.putConstraint(SpringLayout.NORTH, newTaskDialogOkButton, -25, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogOkButton, 10, SpringLayout.WEST, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.SOUTH, newTaskDialogOkButton, 0, SpringLayout.SOUTH, contentPanel);
			sl_contentPanel.putConstraint(SpringLayout.EAST, newTaskDialogOkButton, 120, SpringLayout.WEST, contentPanel);
			contentPanel.add(newTaskDialogOkButton);
			newTaskDialogOkButton.setActionCommand("OK");
			getRootPane().setDefaultButton(newTaskDialogOkButton);
			newTaskDialogOkButton.addActionListener(new newTaskDialogOkButtonListener());
		}
		
		this.setNewTaskDialogDateChooser(new JDateChooser(dateChosenByUser));
		sl_contentPanel.putConstraint(SpringLayout.NORTH, newTaskDialogDateChooser, 10, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, newTaskDialogDateChooser, -206, SpringLayout.SOUTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.EAST, newTaskDialogDateChooser, -222, SpringLayout.EAST, contentPanel);
		contentPanel.add(newTaskDialogDateChooser);
		
		JLabel newTaskDialogDateLable = new JLabel("Date");
		sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogDateChooser, 30, SpringLayout.EAST, newTaskDialogDateLable);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, newTaskDialogDateLable, 25, SpringLayout.NORTH, contentPanel);
		sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogDateLable, 0, SpringLayout.WEST, newTaskDialogOkButton);
		contentPanel.add(newTaskDialogDateLable);
		
		newTaskDialogTitleTextField = new JTextField();
		sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogTitleTextField, 0, SpringLayout.WEST, newTaskDialogDateChooser);
		contentPanel.add(newTaskDialogTitleTextField);
		newTaskDialogTitleTextField.setColumns(10);
		
		JLabel newTaskDialogTitleLable = new JLabel("Title:");
		sl_contentPanel.putConstraint(SpringLayout.NORTH, newTaskDialogTitleLable, 84, SpringLayout.SOUTH, newTaskDialogDateLable);
		sl_contentPanel.putConstraint(SpringLayout.NORTH, newTaskDialogTitleTextField, -3, SpringLayout.NORTH, newTaskDialogTitleLable);
		sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogTitleLable, 0, SpringLayout.WEST, newTaskDialogOkButton);
		contentPanel.add(newTaskDialogTitleLable);
		
		this.setNewTaskDialogTimeSpinner(new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.HOUR_OF_DAY)));
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, newTaskDialogTimeSpinner, -21, SpringLayout.NORTH, newTaskDialogTitleTextField);
		sl_contentPanel.putConstraint(SpringLayout.EAST, newTaskDialogTimeSpinner, 0, SpringLayout.EAST, newTaskDialogTitleTextField);
		newTaskDialogTimeSpinner.setLocale(Locale.ENGLISH);
		newTaskDialogTimeSpinner.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		newTaskDialogTimeSpinner.setEditor(new JSpinner.DateEditor(newTaskDialogTimeSpinner, "HH:mm:ss"));
		contentPanel.add(newTaskDialogTimeSpinner);
		

		JLabel newTaskDialogTimeLable = new JLabel("Time");
		sl_contentPanel.putConstraint(SpringLayout.WEST, newTaskDialogTimeLable, 0, SpringLayout.WEST, newTaskDialogOkButton);
		sl_contentPanel.putConstraint(SpringLayout.SOUTH, newTaskDialogTimeLable, 0, SpringLayout.SOUTH, newTaskDialogTimeSpinner);
		contentPanel.add(newTaskDialogTimeLable);
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new GridLayout(0, 2, 0, 0));
		}
		this.setVisible(true);
	}
	
	public JSpinner getNewTaskDialogTimeSpinner() {
		return newTaskDialogTimeSpinner;
	}

	public void setNewTaskDialogTimeSpinner(JSpinner newTaskDialogTimeSpinner) {
		this.newTaskDialogTimeSpinner = newTaskDialogTimeSpinner;
	}

	public JDateChooser getNewTaskDialogDateChooser() {
		return newTaskDialogDateChooser;
	}

	public void setNewTaskDialogDateChooser(JDateChooser newTaskDialogDateChooser) {
		this.newTaskDialogDateChooser = newTaskDialogDateChooser;
	}

	public MainContentWindowPresenter getParentWindow() {
		return parentWindow;
	}

	public void setParentWindow(MainContentWindowPresenter parentWindow) {
		this.parentWindow = parentWindow;
	}

	/**
	 * Listener class for newTaskDialogCancelButtonListener button
	 */
	private class newTaskDialogCancelButtonListener implements ActionListener{
		
		JDialog thisDialog;
		
		newTaskDialogCancelButtonListener(JDialog thisDialog) {
			this.thisDialog = thisDialog;
		}
		
		/**
		 * Executes when newTaskDialogCancelButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			thisDialog.dispose();
		}
		
	}
	
	/**
	 * Listener class for newTaskDialogCancelButtonListener button
	 */
	private class newTaskDialogOkButtonListener implements ActionListener{
		
		/**
		 * Executes when ewTaskDialogCancelButton button is pressed
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			Date date = getNewTaskDialogDateChooser().getDate();
			Date time = (Date) getNewTaskDialogTimeSpinner().getValue();
			date.setHours(time.getHours());
			date.setMinutes(time.getMinutes());
			date.setSeconds(time.getSeconds());
			System.out.println(date);
			getParentWindow().createNewTask(date, newTaskDialogTitleTextField.getText());

			
		}
		
	}
}
