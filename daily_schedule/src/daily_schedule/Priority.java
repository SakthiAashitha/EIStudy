package daily_schedule;
/*Design Pattern:
**Enum Pattern**: Used to define a set of named constants representing priority levels.
*/
public enum Priority {
	//The Priority enum represents the different levels of task priority.
	HIGH,MEDIUM,LOW;
	
	//A string representing the priority level.
	@Override
	public String toString() {
        return name();
    }
}
