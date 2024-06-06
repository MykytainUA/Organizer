package server.manager;

import java.io.IOException;
import java.sql.*;
import java.util.Date;
import java.text.SimpleDateFormat;

import shared.classes.Task;
import shared.messages.*;

public class DatabaseManager {
	private String dbURL = "jdbc:mysql://localhost:3306/organizerDB";
	private String dbUsername = "root";
	private String dbPass = "";
	
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	
	private LogManager logManager;
	
	private Connection getConnection() {
		return connection;
	}

	private void setConnection(Connection connection) {
		this.connection = connection;
	}

	private LogManager getLogManager() {
		return logManager;
	}

	private void setLogManager(LogManager logManager) {
		this.logManager = logManager;
	}

	public DatabaseManager(LogManager logManager) {
		
		this.setLogManager(logManager);
		
		try {		
			connection = DriverManager.getConnection(dbURL, dbUsername, dbPass);
			
			if(connection != null) {
				System.out.println("DB_MANAGER:Connected to the database");
				this.getLogManager().writeToLogs("DB_MANAGER:Connected to the database");
			}
			
		} catch(SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Could not connect to the database!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Could not connect to the database!!!");
			System.out.println(e);
		}
	}
	
	/**
	 * 
	 * @param message information about sign in
	 * @return ID if sign in was successful or -1 of no such record was found
	 * @throws IOException
	 */
	public int tryToGetIdBySignIn(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		SignInMessage signInMessage = (SignInMessage) message;
		int ID = -1;
		
		try {
			String request = "SELECT ID FROM `users` WHERE login = ? AND password = ?;";	
			
			PreparedStatement preparedStatement = connection.prepareStatement(request);
			preparedStatement.setString(1, signInMessage.getLogin());
			preparedStatement.setString(2, signInMessage.getPassword());
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			// If no such record in database return -1
			if(!resultSet.next()) {
				signInMessage.setResponseData(-1);
				return -1;
			}
			
			ID = resultSet.getInt("ID");
			
			// If there is another record return -1
			if(resultSet.next()) {
				signInMessage.setResponseData(-1);
				return -1;
			}

		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
		return ID;
	}
	
	/**
	 * 
	 * @param message information about sign up
	 * @return boolean true if record was created false else
	 * @throws IOException
	 */
	public boolean tryToCreateSignUpRecord(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		SignUpMessage signUpMessage = (SignUpMessage) message;
		int ID = -1;
		
		try {
			String request = "INSERT INTO `users` (login, password) VALUES (?, ?)";;	
			
			PreparedStatement preparedStatement = connection.prepareStatement(request);
			preparedStatement.setString(1, signUpMessage.getLogin());
			preparedStatement.setString(2, signUpMessage.getPassword());
			
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the data was inserted successfully
            if (rowsAffected > 0) {
            	this.getLogManager().writeToLogs("DABASE_MANAGER:Data inserted successfully");
                System.out.println("DABASE_MANAGER:Data inserted successfully");
                return true;
            } else {
            	this.getLogManager().writeToLogs("DABASE_MANAGER:Failed to insert data");
                System.out.println("DABASE_MANAGER:Failed to insert data");
                return false;
            }

		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
		
		return false;
	}
	
	public int tryToCreateNewTask(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		AddNewTaskMessage addNewTaskMessage = (AddNewTaskMessage) message;
		
		try {
			// Check for a repeating task
			String request = "SELECT COUNT(*) FROM tasks WHERE ID = ? AND date = ? ";	
			PreparedStatement preparedStatement = connection.prepareStatement(request);
			preparedStatement.setInt(1, addNewTaskMessage.getID());
			Timestamp timestamp = new Timestamp(addNewTaskMessage.getTask().getDate().getTime());
			timestamp.setNanos(0);
			preparedStatement.setTimestamp(2, timestamp);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
			if(rs.getInt(1) != 0) {
				return rs.getInt(1);
			}
			// Create new task
			request = "INSERT INTO `tasks` (ID, date, title, is_finished) VALUES (?, ?, ?, ?)";	
			preparedStatement = connection.prepareStatement(request);
			preparedStatement.setInt(1, addNewTaskMessage.getID());
			preparedStatement.setTimestamp(2, timestamp);
			preparedStatement.setString(3, addNewTaskMessage.getTask().getTitle());
			preparedStatement.setBoolean(4, false);
			
			int rowsAffected = preparedStatement.executeUpdate();

			// Check if the data was inserted successfully
            if (rowsAffected > 0) {
            	this.getLogManager().writeToLogs("DABASE_MANAGER:Data inserted successfully");
                System.out.println("DABASE_MANAGER:Data inserted successfully");
            } else {
            	this.getLogManager().writeToLogs("DABASE_MANAGER:Failed to insert data");
                System.out.println("DABASE_MANAGER:Failed to insert data");
            }
            
            return rs.getInt(1);

		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
		return -1;
	}	
	
	public void tryToGetAllTasksForUser(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		GetAllTasksForThisUserMessage getAllTasksForThisUserMessage = (GetAllTasksForThisUserMessage) message;
		System.out.print(getAllTasksForThisUserMessage);
		
		try {
			// Check for a repeating task
			String request = "SELECT * FROM tasks WHERE ID = ?";	
			PreparedStatement preparedStatement = connection.prepareStatement(request);
			preparedStatement.setInt(1, getAllTasksForThisUserMessage.getID());
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()) {
				Task task = new Task(rs.getString("title"), rs.getTimestamp("date"), rs.getBoolean("is_finished"));
				getAllTasksForThisUserMessage.getTasks().add(task);
			}
			
            


		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
		
	}
	
	public int tryToDeleteSelectedTasks(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		DeleteSelectedTasksMessage deleteSelectedTasksMessage = (DeleteSelectedTasksMessage) message;
		int rowsAffected = 0;
		try {
			for(Task task : deleteSelectedTasksMessage.getTasks()) {
				String request = "DELETE FROM tasks WHERE ID = ? AND date = ? AND title = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(request);
				preparedStatement.setInt(1, deleteSelectedTasksMessage.getID());
				Timestamp timestamp = new Timestamp(task.getDate().getTime());
				timestamp.setNanos(0);
				String pattern = "yyyy-MM-dd HH:mm:ss";

		        // Create a SimpleDateFormat object with the desired pattern
		        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		        // Format the Timestamp object
		        String formattedDate = dateFormat.format(timestamp);
				preparedStatement.setString(2, formattedDate);
				preparedStatement.setString(3, task.getTitle());				
				rowsAffected += preparedStatement.executeUpdate();
			}
			// Check if the data was inserted successfully
			if (rowsAffected > 0) {
				this.getLogManager().writeToLogs("DABASE_MANAGER:Data inserted successfully");
				System.out.println("DABASE_MANAGER:Data inserted successfully");
			} else {
				this.getLogManager().writeToLogs("DABASE_MANAGER:Failed to insert data");
				System.out.println("DABASE_MANAGER:Failed to insert data");
			}
			
			return rowsAffected;
		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
		return 0;
	}
	
	public int tryToChangeTasksStatesToOpposites(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		ChangeTasksStatesToOppositeMessage changeTasksStatesToOppositeMessage = (ChangeTasksStatesToOppositeMessage) message;
		int rowsAffected = 0;
		try {
			for(Task task : changeTasksStatesToOppositeMessage.getTasks()) {
				String request = "UPDATE tasks SET is_finished = ? WHERE ID = ? AND date = ? AND title = ?";
				PreparedStatement preparedStatement = connection.prepareStatement(request);
				preparedStatement.setBoolean(1, !task.isIs_completed());
				preparedStatement.setInt(2, changeTasksStatesToOppositeMessage.getID());
				Timestamp timestamp = new Timestamp(task.getDate().getTime());
				timestamp.setNanos(0);
				String pattern = "yyyy-MM-dd HH:mm:ss";

		        // Create a SimpleDateFormat object with the desired pattern
		        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		        // Format the Timestamp object
		        String formattedDate = dateFormat.format(timestamp);
				preparedStatement.setString(3, formattedDate);
				preparedStatement.setString(4, task.getTitle());				
				rowsAffected += preparedStatement.executeUpdate();
			}
			// Check if the data was inserted successfully
			if (rowsAffected > 0) {
				this.getLogManager().writeToLogs("DABASE_MANAGER:Data inserted successfully");
				System.out.println("DABASE_MANAGER:Data inserted successfully");
			} else {
				this.getLogManager().writeToLogs("DABASE_MANAGER:Failed to insert data");
				System.out.println("DABASE_MANAGER:Failed to insert data");
			}
			
			return rowsAffected;
		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
		return 0;
	}
	
	public void tryToAddMultipleTasks(Message message) throws IOException {
		
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		ManageMultipleTasksMessage manageMultipleTasksMessage = (ManageMultipleTasksMessage) message;
		Date fromDate = manageMultipleTasksMessage.getFrom();
		Date toDate =  manageMultipleTasksMessage.getTo();
		
		try {
			for(long iterationTime = fromDate.getTime(); iterationTime <= toDate.getTime(); iterationTime += Date.UTC(70, 0, 2, 0, 0, 0)) {
				Timestamp timeToInsert =  new Timestamp(iterationTime);
				timeToInsert.setNanos(0);
				// Check for a repeating task
				String request = "SELECT COUNT(*) FROM tasks WHERE ID = ? AND date = ? ";	
				PreparedStatement preparedStatement = connection.prepareStatement(request);
				preparedStatement.setInt(1, manageMultipleTasksMessage.getID());
				preparedStatement.setTimestamp(2, timeToInsert);
				ResultSet rs = preparedStatement.executeQuery();
				System.out.println(rs);
				rs.next();
				if(rs.getInt(1) == 0) {
					// Create new task
					request = "INSERT INTO `tasks` (ID, date, title, is_finished) VALUES (?, ?, ?, ?)";	
					preparedStatement = connection.prepareStatement(request);
					preparedStatement.setInt(1, manageMultipleTasksMessage.getID());
					preparedStatement.setTimestamp(2, timeToInsert);
					preparedStatement.setString(3, manageMultipleTasksMessage.getTitle());
					preparedStatement.setBoolean(4, false);
					preparedStatement.executeUpdate();
				}
			}
		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
	}
	
	public void tryToDeleteMultipleTasks(Message message) throws IOException {
		if(this.getConnection() == null) {
			throw new IOException("DABASE_MANAGER:ERROR:Database is not available");
		}
		
		ManageMultipleTasksMessage manageMultipleTasksMessage = (ManageMultipleTasksMessage) message;
		Date fromDate = manageMultipleTasksMessage.getFrom();
		Date toDate =  manageMultipleTasksMessage.getTo();
		
		try {
			for(long iterationTime = fromDate.getTime(); iterationTime <= toDate.getTime(); iterationTime += Date.UTC(70, 0, 2, 0, 0, 0)) {
				Timestamp timeToDelete =  new Timestamp(iterationTime);
				timeToDelete.setNanos(0);
				String request = "DELETE FROM tasks WHERE ID = ? AND date = ? AND title = ?";
				
				PreparedStatement preparedStatement = connection.prepareStatement(request);
				preparedStatement.setInt(1, manageMultipleTasksMessage.getID());
				
				String pattern = "yyyy-MM-dd HH:mm:ss";

		        // Create a SimpleDateFormat object with the desired pattern
		        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		        String formattedDate = dateFormat.format(timeToDelete);
				preparedStatement.setString(2, formattedDate);
				preparedStatement.setString(3, manageMultipleTasksMessage.getTitle());				
				System.out.println(preparedStatement.executeUpdate());
		        
				
			}
		} catch (SQLException e) {
			this.getLogManager().writeToLogs("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			this.getLogManager().writeToLogs(e.toString());
			System.out.println("DB_MANAGER:ERROR:Unable to execute SQL request!!!");
			System.out.println(e);
		}
	}
}
