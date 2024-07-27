package daily_schedule;

import java.util.Scanner;

public class MainClass {
    public static void main(String[] args) {
        // Create a single instance of ScheduleManager using the Singleton pattern
        // Singleton Pattern: Ensures that only one instance of ScheduleManager exists
        ScheduleManager scheduleManager = ScheduleManager.getInstance();
        
        // Observer Pattern: Add an observer to notify when there's a task conflict
        scheduleManager.addObserver(task -> System.out.println("Error: Task conflicts with existing task \"" + task.getDescription() + "\"."));
        
        Scanner scanner = new Scanner(System.in);
        
        // Command Pattern: Using CommandHandler to manage different operations
        CommandHandler commandHandler = new CommandHandler(scheduleManager, scanner);
        boolean userInput = true;

        while (userInput) {
            // Display available commands to the user
            System.out.println("Enter a command: ADD, REMOVE, VIEW, EDIT, VIEW PRIORITY (vp), EXIT");
            String command = scanner.nextLine().trim().toLowerCase();

            if (command.equals("exit")) {
                userInput = false; // Exit the loop
                System.out.println("Exit");
            } else {
                // Get and execute the corresponding command
                Command cmd = commandHandler.getCommand(command);
                if (cmd != null) {
                    cmd.execute();
                } else {
                    // Handle unrecognized commands
                    System.out.println("Invalid operation. Try again.");
                }
            }
        }

        // Close the scanner to release resources
        scanner.close();
    }
}
