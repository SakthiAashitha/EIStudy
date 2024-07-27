package daily_schedule;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class TaskFactory {
	/**This method encapsulates the logic for creating a Task object
	 Factory method to create a Task object.
	 */
	 
	public static Task createTask(String description,String startTime,String endTime,String priority) {
		LocalTime start ;
		LocalTime end ;
		// Parse start and end times to validate and convert them into LocalTime objects
		try {
			//to validate time of the task in HH:mm format
			start=LocalTime.parse(startTime);
			end=LocalTime.parse(endTime);
		}catch(DateTimeParseException e){
			// If the time format is incorrect, throw an exception
			throw new IllegalArgumentException("Invalid time format");

		}
		 // Return a new Task object with the parsed LocalTime and other details
		return new Task(description,start,end,priority) ;
	}
}
