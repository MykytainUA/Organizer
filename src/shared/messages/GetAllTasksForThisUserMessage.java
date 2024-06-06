package shared.messages;

import java.util.ArrayList;

import shared.classes.Task;


public class GetAllTasksForThisUserMessage extends Message {

	private static final long serialVersionUID = 7166845497230090644L;
	
	private int ID;
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public GetAllTasksForThisUserMessage(int ID) {
		this.setRequesType(RequestType.GET_ALL_TASKS_FOR_THIS_USER);
		this.setDataUpdated(false);
		this.setID(ID);
		this.setTasks(new ArrayList<Task>());
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
	
	@Override
	public void shallowCopy(Message newMessage) {
		this.setDataUpdated(newMessage.isDataUpdated());
		this.setRequesType(newMessage.getRequesType());
		this.setResponseState(newMessage.getResponseState());
		
		if(newMessage instanceof GetAllTasksForThisUserMessage) {
			GetAllTasksForThisUserMessage newMessageCasted = (GetAllTasksForThisUserMessage) newMessage;
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
