package behaviouralDesignPattern;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// Handler Interface
// This interface defines a method for setting the next handler in the chain
// and a method for parsing the date.
interface DateParser {
    void setNext(DateParser next);
    void parseDate(String dateStr);
}

// Concrete Handlers
// DateFormatHandler attempts to parse a date using a specific format.
// If it fails, it passes the request to the next handler in the chain.
class DateFormatHandler implements DateParser {
    private DateParser next;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void setNext(DateParser next) {
        this.next = next;
    }

    @Override
    public void parseDate(String dateStr) {
        try {
            Date date = format.parse(dateStr);
            System.out.println("Parsed date: " + date);
        } catch (ParseException e) {
            if (next != null) next.parseDate(dateStr); // Pass to next handler if parsing fails
            else System.out.println("Unparseable date: " + dateStr);
        }
    }
}

// Client
// The main class demonstrates the use of the Chain of Responsibility pattern
// by setting up a chain of date parsers and allowing user input to be parsed
// through the chain.
public class ChainOfResponsibilityPattern {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateParser handler1 = new DateFormatHandler();
        DateParser handler2 = new DateFormatHandler(); // Example of a second handler
        handler1.setNext(handler2);

        String input;
        System.out.println("Date Parser using Chain of Responsibility Pattern");
        System.out.println("Enter date in 'yyyy-MM-dd' format or type 'exit' to quit:");

        do {
            System.out.print("Enter date: ");
            input = scanner.nextLine();

            if (!input.equalsIgnoreCase("exit")) {
                handler1.parseDate(input);
            } else {
                System.out.println("Exiting the program.");
            }

        } while (!input.equalsIgnoreCase("exit"));

        // Close the scanner
        scanner.close();
    }
}
