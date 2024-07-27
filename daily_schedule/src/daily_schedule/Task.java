package daily_schedule;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/*Design Pattern:
**Data Transfer Object (DTO)**: The "Task" class serves as a DTO, encapsulating task-related data to be transferred between different parts of the application.
*/
public class Task {
	private String description;
	private LocalTime startTime;
	private LocalTime endTime;
	private String priority;
	
	//Constructor to create a new task with the specified attributes.
	public Task(String description,LocalTime startTime,LocalTime endTime,String priority) {
		this.description=description;
		this.startTime=startTime;
		this.endTime=endTime;
		this.priority=priority;
	}
	
	//**Data Access**: The class provides getter and setter methods to access and modify these attributes.
	public String getDescription() {//getter Method
		return description;
	}
	public void setDescription(String description) { //setter Method
		this.description=description;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime=startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime=endTime;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority=priority;
	}
	
}
