package shared.messages;

import java.util.Date;
import shared.classes.Task;

public class AddNewTaskMessage extends Message {

	private static final long serialVersionUID = 9188520505650280715L;
	
	private int ID = -1;
	private Date date = null;
	private String title = "";
	private Task task = null;;
	
	public AddNewTaskMessage(int ID, Task task) {
		this.setRequesType(RequestType.ADD_NEW_TASK);
		this.setDataUpdated(false);
		this.setID(ID);
		this.setTask(task);
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	@Override
	public void shallowCopy(Message newMessage) {
		this.setDataUpdated(newMessage.isDataUpdated());
		this.setRequesType(newMessage.getRequesType());
		this.setResponseState(newMessage.getResponseState());
		
		if(newMessage instanceof AddNewTaskMessage) {
			AddNewTaskMessage newMessageCasted = (AddNewTaskMessage) newMessage;
			this.getTask().shallowCopy(newMessageCasted.getTask());
			this.setID(newMessageCasted.getID());
		} else {
			System.out.println("Error: During copying messages error occured message and newMessage have different types!!!");
		}
	}
	
	public void dateAlreadyOccupied() {
		this.setDataUpdated(true);
		this.setResponseState(ResponseState.DATE_ALREADY_OCCUPIED);
	}

	@Override
	public String toString() {
		String stringObj = super.toString() + "\n";
		stringObj += "ID:" + this.getID() + "\n";
		stringObj += "Task:" + getTask().toString();
		return stringObj;
	}

}
