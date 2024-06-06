package shared.classes;
import java.util.Date;

import java.io.Serializable;

public class Task implements Serializable{
	private String title = "";
	private Date date = null;
	private boolean is_completed;
	
	public Task(String title, Date date, boolean is_completed) {
		this.setTitle(title);
		this.setDate(date);
		this.setIs_completed(is_completed);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isIs_completed() {
		return is_completed;
	}

	public void setIs_completed(boolean is_completed) {
		this.is_completed = is_completed;
	}
	
	public void shallowCopy(Task newTask) {
		this.setDate(newTask.getDate());
		this.setTitle(newTask.getTitle());
		this.setIs_completed(newTask.isIs_completed());
	}
	
	@Override
	public String toString() {
		String stringObj = "Date:" + this.getDate() + "\n";
		stringObj += "Title:" + this.getTitle() + "\n";
		stringObj += "Boolean:" + this.isIs_completed() + "\n";
		return stringObj;
	}
}
