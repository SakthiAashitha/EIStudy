package behaviouralDesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Observer Interface
// Observers implement this interface to get notified of changes in the subject's state.
interface Observer {
    void update(String status);
}

// Concrete Observer
// Stakeholders are the concrete observers that get notified of ticket status changes.
class Stakeholder implements Observer {
    private String name;

    public Stakeholder(String name) {
        this.name = name;
    }

    @Override
    public void update(String status) {
        // Notify the stakeholder of the ticket status change.
        switch (status.toLowerCase()) {
            case "open":
                System.out.println(name + " notified: A new ticket has been opened.");
                break;
            case "in progress":
                System.out.println(name + " notified: The ticket is currently being worked on.");
                break;
            case "resolved":
                System.out.println(name + " notified: The ticket has been resolved.");
                break;
            default:
                System.out.println(name + " notified: Unknown status - " + status);
        }
    }
}

// Subject
// The Ticket class represents the subject that maintains a list of observers.
class Ticket {
    private List<Observer> observers = new ArrayList<>();

    // Add an observer to the list.
    public void addObserver(Observer o) {
        observers.add(o);
    }

    // Set the status of the ticket and notify all observers.
    public void setStatus(String status) {
        System.out.println("Ticket status: " + status);
        for (Observer o : observers) {
            o.update(status);
        }
    }
}

// Client Code
// The main class demonstrates the use of the Observer pattern by adding stakeholders
// and updating ticket status to notify stakeholders.
public class ObserverPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ticket ticket = new Ticket();
        boolean isValid = true;

        // Loop to handle user input until they choose to exit
        while (isValid) {
            System.out.println("Design Pattern in use: Observer Pattern");
            System.out.println("Choose an option:");
            System.out.println("1. Add a stakeholder");
            System.out.println("2. Update ticket status");
            System.out.println("3. Exit");
            System.out.print("Enter your choice (1/2/3): ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // Add a new stakeholder
                    System.out.println("Users can add stakeholders who will be notified of status changes.");
                    System.out.println("Enter the name of the stakeholder:");
                    String name = scanner.nextLine();
                    ticket.addObserver(new Stakeholder(name));
                    System.out.println("Stakeholder added: " + name);
                    break;

                case "2":
                    // Update ticket status
                    System.out.println("Users can update the ticket status, which will notify all added stakeholders.");
                    System.out.println("Enter ticket status (Open, In Progress, Resolved):");
                    String status = scanner.nextLine();

                    // Validate and normalize status input
                    while (!status.equalsIgnoreCase("Open") &&
                           !status.equalsIgnoreCase("In Progress") &&
                           !status.equalsIgnoreCase("Resolved")) {
                        System.out.println("Invalid status. Please enter one of the following: Open, In Progress, Resolved.");
                        status = scanner.nextLine();
                    }

                    // Update the status, which will notify observers
                    ticket.setStatus(status);
                    break;

                case "3":
                    // Exit the program
                    System.out.println("Exiting the program.");
                    isValid = false;
                    scanner.close();
                    return; // Exit the main method

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }
    }
}
