/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package hospitalsystem;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
/**
 *
 * @author Jaycee Kristine
 */


public class HospitalSystem {
    // shared application lists/queues
    public static final Scanner scanner = new Scanner(System.in);
    public static final List<Person> patients = new ArrayList<>();
    public static final QueueManager queueManager = new QueueManager();

    // only allow addPatient if a call has been made
    private static PriorityNumber lastCalled = null;

    // patient ID auto-increment
    private static int patientCounter = 1;

    // hardcoded login credentials
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "1234";

    public static void main(String[] args) {

        if (!login()) {
            System.out.println("Login failed. Exiting program.");
            return;
        }

        while (true) {
            clearScreen();
            printLine();
            System.out.println(" HOSPITAL PATIENT INFORMATION SYSTEM");
            printLine();
            System.out.println("1. Get Priority Number");
            System.out.println("2. View Queue");
            System.out.println("3. Call Next Patient");
            System.out.println("4. Add Patient (Only when called)");
            System.out.println("5. Patient Management Menu");
            System.out.println("6. Exit");
            System.out.println("7. Logout");
            printLinesingle();
           System.out.print("Enter your choice (shortcut key allowed): ");
String input = scanner.nextLine().trim().toUpperCase();

switch (input) {
    case "1", "G" -> queueManager.issuePriority(scanner); // G = Get Priority Number
    case "2", "V" -> queueManager.viewQueue();            // V = View Queue
    case "3", "C" -> {                                     // C = Call Next
        PriorityNumber called = queueManager.callNext();
        if (called != null) {
            lastCalled = called;
            System.out.println("\nProceed to registration desk to add patient info (Option 4).");
        }
        pause();
    }
    case "4", "A" -> {                                     // A = Add Patient
        if (lastCalled == null) {
            System.out.println("No patient has been called. Call a patient first (Option 3).");
            pause();
        } else {
            String id = String.format("P-%03d", patientCounter++);
            HospitalData.addPatient(scanner, patients, id, lastCalled);
            lastCalled = null; // reset
            pause();
        }
    }
    case "5", "M" -> PatientManagement.menu(scanner, patients); // M = Patient Management
    case "6", "E" -> {                                      // E = Exit
        clearScreen();
        printLine();
        System.out.println(" Exiting system... Goodbye!");
        printLine();
        return;
    }
    default -> {
        System.out.println("Invalid choice, please try again.");
        pause();
    }
}

        }
    }

    // LOGIN METHOD
    public static boolean login() {
        clearScreen();
        printLine();
        System.out.println(" HOSPITAL SYSTEM LOGIN");
        printLine();

        int attempts = 3; // allow 3 login attempts
        while (attempts > 0) {
            System.out.print("Username: ");
            String user = scanner.nextLine().trim();
            System.out.print("Password: ");
            String pass = scanner.nextLine().trim();

            if (USERNAME.equals(user) && PASSWORD.equals(pass)) {
                System.out.println("Login successful!");
                pause();
                return true;
            } else {
                attempts--;
                System.out.println("Invalid credentials. Attempts left: " + attempts);
            }
        }
        return false;
    }

    // small utilities
    public static int getIntInput() {
        while (true) {
            try {
                String s = scanner.nextLine().trim();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    public static void printLine() {
        System.out.println("========================================");
    }

    public static void printLinesingle() {
        System.out.println("----------------------------------------");
    }

    public static void pause() {
        System.out.println("\nPress [Enter] to continue...");
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}