package daily_schedule;
//import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.Comparator;
import java.util.stream.Collectors;
//import java.util.function.Predicate;

/*The ScheduleManager class is a singleton that manages tasks, handles task-related operations,
*and notifies observers of task conflicts.
*/
public class ScheduleManager {
	
	private List<Task> tasks;
	private List<ConflictObserver> observers;
	private static ScheduleManager instance;
	
	//Private constructor to prevent instantiation. Initializes task and observer lists.
	private ScheduleManager() {
		tasks = new ArrayList<>();
		observers = new ArrayList<>();
	}
	
	
	/* Singleton Pattern: Ensures that only one instance of ScheduleManager exists.
	*Provides the single instance of ScheduleManager.
	*/
	public static ScheduleManager getInstance(){
		if(instance == null) {
			instance = new ScheduleManager();
		}
		//return the single instance of ScheduleManager.
		return instance;
	}
	
	//Adds an observer that will be notified of task conflicts.
	public void addObserver(ConflictObserver observer) {
		observers.add(observer);
	}
	
	//Observer Pattern: Allows observers to be notified of task conflicts.
	private void notifyObservers(Task conflictingTask) {
		for(ConflictObserver observer:observers) {
			observer.notifyConflict(conflictingTask);
		}
	}
	
	//Adds a task to the schedule if it does not conflict with existing tasks.
	public void addTask(Task task) {
		//boolean hasConflict=tasks.stream().anyMatch(previousTask ->isOverLap(previousTask,task));
		Optional<Task> conflictingTask = tasks.stream()
				.filter(previousTask->isOverLap(previousTask,task))
				.findFirst();
		conflictingTask.ifPresentOrElse(previousTask->notifyObservers(previousTask),()->{tasks.add(task);
		System.out.println("Task added successfully. No conflicts.");
		});
	}
	
	//Removes a task based on its description.
	public void removeTask(String removeDescription) {
		Task tempTask = new Task(removeDescription,null,null,null);
		Optional<Task> taskToRemove=tasks.stream()
				.filter(task->task.getDescription()
						.equalsIgnoreCase(removeDescription))
				.findFirst();
		taskToRemove.ifPresentOrElse(task->{tasks.remove(task);
		System.out.println("Task removed successfully.");},
				()->System.out.println("Error: Task not found."));
		
	}
	/*private boolean hasTask(String removeDescription) {
		boolean removed=tasks.stream().anyMatch(description->description.getDescription().equalsIgnoreCase(removeDescription));
		return removed;
	}
	*/
	
	//Views tasks filtered by priority and sorted by start time.
	public void viewByPriority(String viewPriority) {
		Priority priority;
	    try {
	        priority = Priority.valueOf(viewPriority.toUpperCase());
	    } catch (IllegalArgumentException e) {
	        System.out.println("Error: Invalid priority level.");
	        return;
	    }
		List<Task>filterList=tasks.stream()
				.filter(task->task.getPriority().equalsIgnoreCase(priority.name()))
				.collect(Collectors.toList());
		if(filterList.isEmpty()) {
		 
	       System.out.println("No tasks with priority " + viewPriority + " scheduled.");
		}else {
        filterList.stream()
            .sorted(Comparator.comparing(Task::getStartTime))
            .forEach(task -> System.out.printf("%s - %s %s [%s]%n",
                    task.getStartTime(), task.getEndTime(), task.getDescription(), task.getPriority()));
    }
	}
	
	//Views all tasks sorted by start time.
	public void viewTask() {
		if(tasks.isEmpty()) {
			System.out.println("No tasks scheduled for the day.");
			return;
		}
		tasks.sort(Comparator.comparing(Task::getStartTime));
		for(Task t:tasks) {
		System.out.printf("%s -%s %s [%s]%n",t.getStartTime(),t.getEndTime(),t.getDescription(),t.getPriority());
		}
	}
	
	//Edits an existing task based on its old description and replaces it with a new task.
	public void editTask(String oldDescription, Task newTask) {
	    boolean isOverlap = false;

	    // Check for overlap with other tasks
	    for (Task t : tasks) {
	        if (!t.getDescription().equals(oldDescription) && isOverLap(t, newTask)) {
	            isOverlap = true;
	            break;
	        }
	    }

	    if (!isOverlap) {
	        // Find the task with the old description
	        Optional<Task> taskOptional = tasks.stream()
	                .filter(task -> task.getDescription().equals(oldDescription))
	                .findFirst();

	        // Replace the task if found
	        if (taskOptional.isPresent()) {
	            int index = tasks.indexOf(taskOptional.get());
	            tasks.set(index, newTask);
	            System.out.println("Task edited successfully.");
	        } else {
	            System.out.println("Error: Task not found.");
	        }
	    } else {
	        System.out.println("Error: The new task overlaps with an existing task.");
	    }
	}
		
	public Task findTask(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                return task;
            }
        }
        return null;
    }
	
	//**Strategy Pattern**: The task conflict checking strategy is implemented in the method isOverLap.
	
	//Checks if two tasks overlap in time.
	private boolean isOverLap(Task task1, Task task2) {
		return !(task1.getEndTime().compareTo(task2.getStartTime())<=0||task2.getEndTime().compareTo(task1.getStartTime())<=0);
	}
}
