package creationalDesignPattern;

import java.util.Scanner;


//Product Interface
//Defines the common interface for the products created by the factories.
interface Document {
 void open();
 void write(String content); // Added method to write content to the document
}

//Concrete Products
//WordDocument is a concrete implementation of the Document interface.
class WordDocument implements Document {
 @Override
 public void open() {
     System.out.println("Word document opened.");
 }
 
 @Override
 public void write(String content) {
     System.out.println("Writing to Word document: " + content);
 }
}

//PdfDocument is another concrete implementation of the Document interface.
class PdfDocument implements Document {
 @Override
 public void open() {
     System.out.println("PDF document opened.");
 }
 
 @Override
 public void write(String content) {
     System.out.println("Writing to PDF document: " + content);
 }
}

//ExcelDocument is another concrete implementation of the Document interface.
class ExcelDocument implements Document {
 @Override
 public void open() {
     System.out.println("Excel spreadsheet opened.");
 }
 
 @Override
 public void write(String content) {
     System.out.println("Writing to Excel spreadsheet: " + content);
 }
}

//PowerPointDocument is another concrete implementation of the Document interface.
class PowerPointDocument implements Document {
 @Override
 public void open() {
     System.out.println("PowerPoint presentation opened.");
 }
 
 @Override
 public void write(String content) {
     System.out.println("Writing to PowerPoint presentation: " + content);
 }
}

//Creator Abstract Class
//DocumentFactory declares the factory method, which is used to create documents.
abstract class DocumentFactory {
 // Factory method to be implemented by concrete factories to create a Document.
 abstract Document createDocument();
}

//Concrete Factories
//WordDocumentFactory is a concrete factory that creates WordDocument instances.
class WordDocumentFactory extends DocumentFactory {
 @Override
 Document createDocument() {
     return new WordDocument();
 }
}

//PdfDocumentFactory is a concrete factory that creates PdfDocument instances.
class PdfDocumentFactory extends DocumentFactory {
 @Override
 Document createDocument() {
     return new PdfDocument();
 }
}

//ExcelDocumentFactory is a concrete factory that creates ExcelDocument instances.
class ExcelDocumentFactory extends DocumentFactory {
 @Override
 Document createDocument() {
     return new ExcelDocument();
 }
}

//PowerPointDocumentFactory is a concrete factory that creates PowerPointDocument instances.
class PowerPointDocumentFactory extends DocumentFactory {
 @Override
 Document createDocument() {
     return new PowerPointDocument();
 }
}

//Client
public class FactoryPattern {
 public static void main(String[] args) {
     Scanner scanner = new Scanner(System.in);
     DocumentFactory factory = null;
     String choice;
     
     do {
         // Display the design pattern being used and menu options to the user
         System.out.println("\nDesign Pattern in use: Factory Pattern");
         System.out.println("Select document type to open:");
         System.out.println("1. Word Document");
         System.out.println("2. PDF Document");
         System.out.println("3. Excel Document");
         System.out.println("4. PowerPoint Document");
         System.out.println("Type 'exit' to quit.");
         System.out.print("Enter your choice (1/2/3/4): ");
         choice = scanner.nextLine();

         // Create the appropriate factory based on user input
         switch (choice) {
             case "1":
                 factory = new WordDocumentFactory();
                 break;
             case "2":
                 factory = new PdfDocumentFactory();
                 break;
             case "3":
                 factory = new ExcelDocumentFactory();
                 break;
             case "4":
                 factory = new PowerPointDocumentFactory();
                 break;
             case "exit":
                 System.out.println("Exiting the program.");
                 continue;
             default:
                 System.out.println("Invalid choice. Please select 1, 2, 3, 4, or type 'exit' to quit.");
                 continue;
         }

         // Create and open the document if a valid factory was selected
         if (factory != null) {
             Document doc = factory.createDocument();
             doc.open();
             
             // Prompt user to write content to the document
             System.out.print("Enter content to write to the document (or type 'skip' to skip writing): ");
             String content = scanner.nextLine();
             
             if (!content.equalsIgnoreCase("skip")) {
                 doc.write(content);
             } else {
                 System.out.println("Skipped writing to the document.");
             }

             logAction(choice); // Log user action
         }

     } while (!choice.equalsIgnoreCase("exit")); // Continue until the user types 'exit'

     // Close the scanner
     scanner.close();
 }

 // Method to log user actions
 private static void logAction(String choice) {
     switch (choice) {
         case "1":
             System.out.println("User opened and potentially wrote to a Word document.");
             break;
         case "2":
             System.out.println("User opened and potentially wrote to a PDF document.");
             break;
         case "3":
             System.out.println("User opened and potentially wrote to an Excel document.");
             break;
         case "4":
             System.out.println("User opened and potentially wrote to a PowerPoint document.");
             break;
     }
 }
}
