package daily_schedule;

import java.util.Scanner;

// Command interface representing an executable command
interface Command {
    void execute();
}

// CommandHandler class to handle different user commands
public class CommandHandler {
    private ScheduleManager scheduleManager;
    private Scanner scanner;

    // Constructor initializing ScheduleManager and Scanner
    public CommandHandler(ScheduleManager scheduleManager, Scanner scanner) {
        this.scheduleManager = scheduleManager;
        this.scanner = scanner;
    }

    // AddTaskCommand: Command for adding a task
    public class AddTaskCommand implements Command {
        @Override
        public void execute() {
            System.out.println("Enter the Description: ");
            String description = scanner.nextLine().trim();
            System.out.println("Enter the Start Time (HH:mm): ");
            String startTime = scanner.nextLine().trim();
            System.out.println("Enter the End Time (HH:mm): ");
            String endTime = scanner.nextLine().trim();
            System.out.println("Enter the Priority Level: ");
            String priority = scanner.nextLine().trim();
            try {
                // Validate time and priority
                Validator.validateTime(startTime);
                Validator.validateTime(endTime);
                Validator.validateTimeOrder(startTime, endTime);
                Validator.validatePriority(priority);
                // Create and add the task using TaskFactory
                Task task = TaskFactory.createTask(description, startTime, endTime, priority);
                scheduleManager.addTask(task);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // RemoveTaskCommand: Command for removing a task
    public class RemoveTaskCommand implements Command {
        @Override
        public void execute() {
            System.out.println("Enter the Description to remove: ");
            String removeDescription = scanner.nextLine().trim();
            scheduleManager.removeTask(removeDescription);
        }
    }

    // ViewTaskCommand: Command for viewing all tasks
    public class ViewTaskCommand implements Command {
        @Override
        public void execute() {
            scheduleManager.viewTask();
        }
    }

    // EditTaskCommand: Command for editing an existing task
    public class EditTaskCommand implements Command {
        @Override
        public void execute() {
            // Search for the task before editing
            System.out.println("Enter the Description of the task to edit:");
            String oldDescription = scanner.nextLine().trim();
            Task existingTask = scheduleManager.findTask(oldDescription);

            if (existingTask == null) {
                System.out.println("Task with description \"" + oldDescription + "\" not found.");
                return;
            }

            System.out.println("Enter the new Description, start Time (HH:mm), end Time (HH:mm), and priority:");
            System.out.println("Enter the Description: ");
            String newDescription = scanner.nextLine().trim();
            System.out.println("Enter the Start Time (HH:mm): ");
            String newStartTime = scanner.nextLine().trim();
            System.out.println("Enter the End Time (HH:mm): ");
            String newEndTime = scanner.nextLine().trim();
            System.out.println("Enter the Priority Level: ");
            String newPriority = scanner.nextLine().trim();
            try {
                // Validate the new task's time and priority
                Validator.validateTime(newEndTime);
                Validator.validateTime(newStartTime);
                Validator.validateTimeOrder(newStartTime, newEndTime);
                Validator.validatePriority(newPriority);
                // Create the new task and update the existing one
                Task newTask = TaskFactory.createTask(newDescription, newStartTime, newEndTime, newPriority);
                scheduleManager.editTask(oldDescription, newTask);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ViewByPriorityCommand: Command for viewing tasks by priority
    public class ViewByPriorityCommand implements Command {
        @Override
        public void execute() {
            System.out.println("Enter the Priority level:");
            String viewPriority = scanner.nextLine();
            try {
                // Validate the priority input and display tasks
                Validator.validatePriority(viewPriority);
                scheduleManager.viewByPriority(viewPriority);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // Factory method to create commands based on user input
    public Command getCommand(String command) {
        switch (command.toLowerCase()) {
            case "add":
                return new AddTaskCommand();
            case "remove":
                return new RemoveTaskCommand();
            case "view":
                return new ViewTaskCommand();
            case "edit":
                return new EditTaskCommand();
            case "view priority":
            case "vp":
                return new ViewByPriorityCommand();
            default:
                return null; // Invalid command
        }
    }
}
