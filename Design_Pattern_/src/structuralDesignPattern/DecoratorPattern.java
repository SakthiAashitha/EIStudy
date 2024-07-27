package structuralDesignPattern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Product Interface
// Defines the common interface for the products created by the factories.
interface Employee {
    String getPosition();
    double getSalary();
}

// Concrete Component
// BasicEmployee is a concrete implementation of the Employee interface.
// It represents an employee with a basic position and salary.
class BasicEmployee implements Employee {
    private String position;
    private double salary;

    public BasicEmployee(String position, double salary) {
        this.position = position;
        this.salary = salary;
    }

    @Override
    public String getPosition() {
        return position;
    }

    @Override
    public double getSalary() {
        return salary;
    }
}

// Decorator
// EmployeeDecorator provides a base for additional functionalities to be added
// to Employee objects without changing their structure.
abstract class EmployeeDecorator implements Employee {
    protected Employee decoratedEmployee;

    public EmployeeDecorator(Employee decoratedEmployee) {
        this.decoratedEmployee = decoratedEmployee;
    }

    @Override
    public String getPosition() {
        return decoratedEmployee.getPosition();
    }

    @Override
    public double getSalary() {
        return decoratedEmployee.getSalary();
    }
}

// Concrete Decorator
// BonusDecorator adds bonus functionality to the basic Employee.
// It extends the functionalities of the base Employee.
class BonusDecorator extends EmployeeDecorator {
    private double bonus;

    public BonusDecorator(Employee decoratedEmployee, double bonus) {
        super(decoratedEmployee);
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        // Adds the bonus to the basic salary.
        return decoratedEmployee.getSalary() + bonus;
    }
}

// Client
// Demonstrates the Decorator Pattern by allowing users to create employees
// with or without additional bonuses and view their details.
public class DecoratorPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> positions = List.of("Developer", "Manager", "Analyst", "Tester");
        List<Employee> employees = new ArrayList<>();
        boolean isValid=true;
        String choice;

        System.out.println("Design Pattern in use: Decorator Pattern");

        do {
            // Display the main menu
            System.out.println("\nMain Menu:");
            System.out.println("1. Create Employee");
            System.out.println("2. View Employee Details");
            System.out.println("Type 'exit' to quit.");
            System.out.print("Enter your choice (1/2): ");
            choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("exit")) {
            	
            	isValid=false;
            	break;
            }

            switch (choice) {
                case "1":
                    // Display list of positions and get user input
                    System.out.println("\nAvailable Positions:");
                    for (int i = 0; i < positions.size(); i++) {
                        System.out.println((i + 1) + ". " + positions.get(i));
                    }
                    System.out.println("Select a position (1-4):");
                    String posChoice = scanner.nextLine();
                    
                    int positionIndex;
                    try {
                        positionIndex = Integer.parseInt(posChoice) - 1;
                        if (positionIndex < 0 || positionIndex >= positions.size()) {
                            System.out.println("Invalid choice. Please select a number between 1 and 4.");
                            continue;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a number between 1 and 4.");
                        continue;
                    }

                    String position = positions.get(positionIndex);

                    System.out.print("Enter employee salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());

                    // Create a basic employee
                    Employee employee = new BasicEmployee(position, salary);

                    // Optionally add bonus
                    System.out.print("Enter bonus amount (or type '0' if no bonus): ");
                    double bonus = Double.parseDouble(scanner.nextLine());

                    if (bonus > 0) {
                        // Apply bonus decorator to the basic employee
                        employee = new BonusDecorator(employee, bonus);
                    }

                    // Add employee to the list
                    employees.add(employee);

                    // Display employee details
                    System.out.println(employee.getPosition() + " with salary: $" + employee.getSalary());
                    break;

                case "2":
                    // View employee details
                    if (employees.isEmpty()) {
                        System.out.println("No employees created yet.");
                    } else {
                        System.out.println("\nEmployee Details:");
                        for (Employee emp : employees) {
                            System.out.println(emp.getPosition() + " with salary: $" + emp.getSalary());
                        }
                    }
                    break;

                default:
                    System.out.println("Invalid choice. Please select 1 or 2.");
                   
                    break;
            }

        } while (isValid);

        // Close the scanner
        scanner.close();
    }
}
