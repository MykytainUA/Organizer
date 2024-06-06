package shared.messages;

import java.util.ArrayList;
import java.util.Date;

import shared.classes.Task;

public class ManageMultipleTasksMessage extends Message{

	private static final long serialVersionUID = -513900015457867200L;
	
	private int ID;
	private Date from;
	private Date to;
	private String title;
	private String action;
	
	public ManageMultipleTasksMessage(int ID, Date from, Date to, String title, String action) {
		this.setRequesType(RequestType.MANAGE_MULTIPLE_TASKS);
		this.setDataUpdated(false);
		this.setID(ID);
		this.setFrom(from);
		this.setTo(to);
		this.setTitle(title);
		this.setAction(action);
	}
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public Date getTo() {
		return to;
	}
	public void setTo(Date to) {
		this.to = to;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public void shallowCopy(Message newMessage) {
		this.setDataUpdated(newMessage.isDataUpdated());
		this.setRequesType(newMessage.getRequesType());
		this.setResponseState(newMessage.getResponseState());
		
		if(newMessage instanceof ManageMultipleTasksMessage) {
			ManageMultipleTasksMessage newMessageCasted = (ManageMultipleTasksMessage) newMessage;
			this.setID(newMessageCasted.getID());
			this.setFrom(newMessageCasted.getFrom());
			this.setTo(newMessageCasted.getTo());
			this.setTitle(newMessageCasted.getTitle());
			this.setAction(newMessageCasted.getAction());
			
		} else {
			System.out.println("Error: During copying messages error occured message and newMessage have different types!!!");
		}
	}
	
	@Override
	public String toString() {
		String stringObj = super.toString() + "\n";
		stringObj += "ID:" + this.getID() + "\n";
		stringObj += "Date from:" + this.getFrom() + "\n";
		stringObj += "Date to:" + this.getTo() + "\n";
		stringObj += "Title:" + this.getTitle() + "\n";
		stringObj += "Action:" + this.getAction();
		return stringObj;
	}
	

}
