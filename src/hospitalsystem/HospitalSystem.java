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

    public static void main(String[] args) {
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
            printLinesingle();
            System.out.print("Enter your choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1 -> queueManager.issuePriority(scanner);
                case 2 -> queueManager.viewQueue();
                case 3 -> {
                    PriorityNumber called = queueManager.callNext();
                    if (called != null) {
                        lastCalled = called;
                        System.out.println("\nProceed to registration desk to add patient info (Option 4).");
                    }
                    pause();
                }
                case 4 -> {
                    if (lastCalled == null) {
                        System.out.println("No patient has been called. Call a patient first (Option 3).");
                        pause();
                    } else {
                        // generate ID
                        String id = String.format("P-%03d", patientCounter++);
                        HospitalData.addPatient(scanner, patients, id, lastCalled);
                        lastCalled = null; // reset
                        pause();
                    }
                }
                case 5 -> PatientManagement.menu(scanner, patients);
                case 6 -> {
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