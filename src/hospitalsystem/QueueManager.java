/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystem;
import java.util.*;

/**
 *
 * @author Jaycee Kristine
 */
public class QueueManager {
     private final Queue<PriorityNumber> emergencyQueue = new LinkedList<>();
    private final Queue<PriorityNumber> normalQueue = new LinkedList<>();

    // counters for priority numbers
    private int normalCounter = 0;
    private int emergencyCounter = 0;

    // issue priority number (prompt for name and type)
    public void issuePriority(Scanner scanner) {
        System.out.println("----------------------------------------");
        System.out.println(" GET PRIORITY NUMBER");
        System.out.println("----------------------------------------");
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine().trim();

        int type;
        while (true) {
            System.out.print("Priority Type (1 = Normal, 2 = Emergency): ");
            try {
                type = Integer.parseInt(scanner.nextLine().trim());
                if (type == 1 || type == 2) break;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Enter 1 or 2.");
        }

        String prefix = (type == 2) ? "E" : "N";
        String priority;
        if (type == 2) {
            emergencyCounter++;
            priority = prefix + "-" + String.format("%03d", emergencyCounter);
            emergencyQueue.add(new PriorityNumber(priority, name, 2));
        } else {
            normalCounter++;
            priority = prefix + "-" + String.format("%03d", normalCounter);
            normalQueue.add(new PriorityNumber(priority, name, 1));
        }

        System.out.println("----------------------------------------");
        System.out.println("Generated Priority Number: " + priority);
        System.out.println("Please wait until your number is called.");
        System.out.println("----------------------------------------");
        System.out.println("Press [Enter] to continue...");
        scanner.nextLine();
    }

    // view queue, emergency first then normal
    public void viewQueue() {
        System.out.println("----------------------------------------");
        System.out.println(" PATIENT QUEUE LIST");
        System.out.println("----------------------------------------");
        System.out.printf("%-9s | %-25s | %s%n", "Priority", "Name", "Status");
        System.out.println("----------------------------------------");

        for (PriorityNumber p : emergencyQueue) {
            System.out.printf("%-9s | %-25s | Waiting%n", p.getPriorityNumber(), p.getPatientName());
        }
        for (PriorityNumber p : normalQueue) {
            System.out.printf("%-9s | %-25s | Waiting%n", p.getPriorityNumber(), p.getPatientName());
        }
        System.out.println("----------------------------------------");
    }

    // call next: emergency has precedence
    public PriorityNumber callNext() {
        PriorityNumber next = !emergencyQueue.isEmpty() ? emergencyQueue.poll() : normalQueue.poll();
        if (next == null) {
            System.out.println("No patients in queue.");
            return null;
        }
        System.out.println("----------------------------------------");
        System.out.println(" CALL NEXT PATIENT");
        System.out.println("----------------------------------------");
        System.out.println("Now Calling: " + next.getPriorityNumber() + " - " + next.getPatientName());
        System.out.println("Proceed to registration desk!");
        System.out.println("----------------------------------------");
        return next;
    }
}
