package shared.messages;

import java.util.ArrayList;

import shared.classes.Task;

public class DeleteSelectedTasksMessage extends Message{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5813031988291279640L;
	
	private int ID;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public DeleteSelectedTasksMessage(int ID, ArrayList<Task> tasks) {
		this.setRequesType(RequestType.DELETE_TASKS);
		this.setDataUpdated(false);
		this.setID(ID);
		this.setTasks(tasks);
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}
	
	public void noTasksWereDeleted() {
		this.setDataUpdated(true);
		this.setResponseState(ResponseState.NO_TASKS_WERE_DELETED);
	}
	
	@Override
	public void shallowCopy(Message newMessage) {
		this.setDataUpdated(newMessage.isDataUpdated());
		this.setRequesType(newMessage.getRequesType());
		this.setResponseState(newMessage.getResponseState());
		
		if(newMessage instanceof DeleteSelectedTasksMessage) {
			DeleteSelectedTasksMessage newMessageCasted = (DeleteSelectedTasksMessage) newMessage;
			this.setTasks(new ArrayList<Task>(newMessageCasted.getTasks()));
			this.setID(newMessageCasted.getID());
		} else {
			System.out.println("Error: During copying messages error occured message and newMessage have different types!!!");
		}
	}
	
	@Override
	public String toString() {
		String stringObj = super.toString() + "\n";
		stringObj += "ID:" + this.getID() + "\n";
		stringObj += "Tasks:" + getTasks().toString();
		return stringObj;
	}

}
