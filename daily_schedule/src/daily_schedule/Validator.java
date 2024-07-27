package daily_schedule;
import java.time.format.DateTimeFormatter;
//import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.EnumSet;

//The Validator class provides utility methods for validating task-related inputs using Utility Pattern.
public class Validator {
	private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
	// EnumSet containing all valid Priority levels
	private static final EnumSet<Priority>priority=EnumSet.allOf(Priority.class);
	public static void validateTime(String time) {
		try {
			// Attempt to parse the time string into a LocalTime object
			LocalTime.parse(time);
			
		}catch(DateTimeParseException e) {
			// Throw an exception if the time format is incorrect
			throw new IllegalArgumentException("Invalid time format.");
		}
	}
	public static void validatePriority(String prio) {
	    // Check if the provided priority string matches any of the valid priorities
			boolean isValid=priority.stream().anyMatch(p -> p.name().equalsIgnoreCase(prio));
			
			// Throw an exception if the priority is not valid
		if(!isValid){
			throw new IllegalArgumentException("Invalid priority level.");
		}
	}
	public static void validateTimeOrder(String startTime, String endTime) throws IllegalArgumentException {
        try {
            LocalTime start = LocalTime.parse(startTime, timeFormatter);
            LocalTime end = LocalTime.parse(endTime, timeFormatter);
            if (end.isBefore(start)) {
                throw new IllegalArgumentException("End time must be after start time.");
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm.");
        }
    }
}
