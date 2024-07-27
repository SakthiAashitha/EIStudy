package creationalDesignPattern;

import java.util.Scanner;

// The Builder Pattern is used to construct a complex object step by step.
// It separates the construction of a complex object from its representation,
// allowing the same construction process to create different representations.

// Product
class Car {
    private String engine;
    private String wheels;
    private String color;

    // Private constructor to enforce the use of the builder
    private Car(Builder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.color = builder.color;
    }

    // Builder class
    public static class Builder {
        private String engine;
        private String wheels;
        private String color;

        // Builder methods for setting various attributes of the Car
        public Builder setEngine(String engine) { this.engine = engine; return this; }
        public Builder setWheels(String wheels) { this.wheels = wheels; return this; }
        public Builder setColor(String color) { this.color = color; return this; }

        // Builds and returns the Car object
        public Car build() { return new Car(this); }
    }

    @Override
    public String toString() {
        return "Car [engine=" + engine + ", wheels=" + wheels + ", color=" + color + "]";
    }
}

// Client
public class BuilderPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String choice;

        // Loop to allow the user to create multiple cars or exit the program
        do {
            // Prompt user for car configuration
            System.out.println("\nDesign Pattern in use: Builder Pattern");
            System.out.println("Enter car configuration");

            System.out.print("Engine name (e.g., V8 Engine): ");
            String engine = scanner.nextLine();

            System.out.print("Number of Wheels: ");
            String wheels = scanner.nextLine();

            System.out.print("Color: ");
            String color = scanner.nextLine();

            // Build the car using the builder
            Car car = new Car.Builder()
                .setEngine(engine)
                .setWheels(wheels)
                .setColor(color)
                .build();

            // Display the built car
            System.out.println("Built Car: " + car);

            // Prompt user to continue or exit
            System.out.print("Type 'exit' to quit or press Enter to build another car: ");
            choice = scanner.nextLine();

        } while (!choice.equalsIgnoreCase("exit")); // Continue until 'exit' is typed

        // Close the scanner
        scanner.close();
    }
}
