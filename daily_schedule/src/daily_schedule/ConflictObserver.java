package daily_schedule;
//The design pattern used in the ConflictObserver class is the Observer Design Pattern.

/*
 * ConflictObserver Interface
 * This interface is a part of the Observer Design Pattern implementation.
 * It is used to define the contract for observers that want to be notified 
 * about conflicts in the schedule, such as overlapping tasks.
 * 
 * Implementing the class will define actions to be taken when a 
 * conflict is detected, such as alerting the user.
 */
public interface ConflictObserver {
	//Method to be called when a task conflict is detected.
    void notifyConflict(Task conflictingTask);
}

