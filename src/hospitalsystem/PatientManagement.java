/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystem;

/**
 *
 * @author Jaycee Kristine
 */
import java.util.*;

public class PatientManagement {
        public static void menu(Scanner scanner, List<Person> patients) {
        while (true) {
            HospitalSystem.clearScreen();
            HospitalSystem.printLine();
            System.out.println(" PATIENT MANAGEMENT");
            HospitalSystem.printLine();
            System.out.println("1. View Patient List");
            System.out.println("2. Search Patient");
            System.out.println("3. Update Patient");
            System.out.println("4. Delete Patient");
            System.out.println("5. Manage Medical Records");
            System.out.println("6. Back to Main Menu");
            HospitalSystem.printLinesingle();
            System.out.print("Enter your choice (shortcut key allowed): ");
            String input = scanner.nextLine().trim().toUpperCase();

            switch (input) {
            case "1", "V" -> viewPatientList(patients);       // V = View Patient List
            case "2", "S" -> searchPatient(scanner, patients); // S = Search Patient
            case "3", "U" -> updatePatient(scanner, patients); // U = Update Patient
            case "4", "D" -> deletePatient(scanner, patients); // D = Delete Patient
            case "5", "R" -> manageRecordsMenu(scanner, patients); // R = Manage Records
            case "6", "M" -> { return; }                        // M = Back to Main Menu
            default -> {
            System.out.println("Invalid choice.");
            HospitalSystem.pause();
            }
        }
    }
    }

    // Display table with requested columns
    private static void viewPatientList(List<Person> patients) {
        HospitalSystem.clearScreen();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("                                              PATIENT LIST");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-10s | %-10s | %-10s | %-3s | %-10s | %-12s | %-12s | %-12s | %-7s%n",
                "Firstname", "Lastname", "Middlename", "Sex", "Age", "Birthdate",
                "Birthplace", "Contact No", "Civil Status", "Records");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------");

        if (patients.isEmpty()) {
            System.out.println("No patients found.");
        } else {
            for (Person p : patients) {
                System.out.printf("%-10s | %-10s | %-10s | %-10s | %-3d | %-10s | %-12s | %-12s | %-12s | %-7d%n",
                        p.getFirstname(), p.getLastname(), p.getMiddlename(),
                        p.getSex(), p.getAge(), p.getBirthdate(), p.getBirthplace(),
                        p.getContactNumber(), p.getCivilStatus(), p.getRecords().size());
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        HospitalSystem.pause();
    }

    private static void searchPatient(Scanner scanner, List<Person> patients) {
        System.out.print("Enter Patient ID or Name: ");
        String query = scanner.nextLine().trim().toLowerCase();
        boolean found = false;
        for (Person p : patients) {
            if (p.getPatientID().equalsIgnoreCase(query) ||
                p.getFullname().toLowerCase().contains(query)) {
                printPatientDetails(p);
                found = true;
            }
        }
        if (!found) System.out.println("No matching patient found.");
        HospitalSystem.pause();
    }

    private static void printPatientDetails(Person p) {
        System.out.println("----------------------------------------");
        System.out.println("ID: " + p.getPatientID());
        System.out.println("Name: " + p.getFullname());
        System.out.println("Sex: " + p.getSex());
        System.out.println("Age: " + p.getAge());
        System.out.println("Birthdate: " + p.getBirthdate());
        System.out.println("Birthplace: " + p.getBirthplace());
        System.out.println("Contact: " + p.getContactNumber());
        System.out.println("Civil Status: " + p.getCivilStatus());
        System.out.println("----------------------------------------");
        System.out.println("Medical Records:");
        if (p.getRecords().isEmpty()) {
            System.out.println("No records found.");
        } else {
            int i = 1;
            for (MedicalRecord r : p.getRecords()) {
                System.out.println(i++ + ". " + r);
            }
        }
    }

    private static void updatePatient(Scanner scanner, List<Person> patients) {
        System.out.print("Enter Patient ID to update: ");
        String id = scanner.nextLine().trim();
        for (Person p : patients) {
            if (p.getPatientID().equalsIgnoreCase(id)) {
                System.out.print("New Firstname (blank to skip): ");
                String fn = scanner.nextLine().trim();
                if (!fn.isBlank()) p.setFirstname(fn);

                System.out.print("New Middlename (blank to skip): ");
                String mn = scanner.nextLine().trim();
                if (!mn.isBlank()) p.setMiddlename(mn);

                System.out.print("New Lastname (blank to skip): ");
                String ln = scanner.nextLine().trim();
                if (!ln.isBlank()) p.setLastname(ln);

                System.out.print("New Age (blank to skip): ");
                String ageStr = scanner.nextLine().trim();
                if (!ageStr.isBlank()) {
                    try { p.setAge(Integer.parseInt(ageStr)); }
                    catch (NumberFormatException ex) { System.out.println("Invalid age input."); }
                }

                System.out.print("New Contact No (blank to skip): ");
                String cn = scanner.nextLine().trim();
                if (!cn.isBlank()) p.setContactNumber(cn);

                System.out.print("New Civil Status (blank to skip): ");
                String cs = scanner.nextLine().trim();
                if (!cs.isBlank()) p.setCivilStatus(cs);

                System.out.println("Patient updated successfully!");
                HospitalSystem.pause();
                return;
            }
        }
        System.out.println("Patient not found.");
        HospitalSystem.pause();
    }

    private static void deletePatient(Scanner scanner, List<Person> patients) {
        System.out.print("Enter Patient ID to delete: ");
        String id = scanner.nextLine().trim();
        Iterator<Person> it = patients.iterator();
        while (it.hasNext()) {
            Person p = it.next();
            if (p.getPatientID().equalsIgnoreCase(id)) {
                System.out.print("Are you sure you want to delete this patient? (Y/N): ");
                String c = scanner.nextLine();
                if (c.equalsIgnoreCase("Y")) {
                    it.remove();
                    System.out.println("Patient deleted.");
                } else {
                    System.out.println("Cancelled.");
                }
                HospitalSystem.pause();
                return;
            }
        }
        System.out.println("Patient not found.");
        HospitalSystem.pause();
    }

    // Medical records menu
    private static void manageRecordsMenu(Scanner scanner, List<Person> patients) {
        System.out.print("Enter Patient ID to manage records: ");
        String id = scanner.nextLine().trim();
        Person target = null;
        for (Person p : patients) if (p.getPatientID().equalsIgnoreCase(id)) { target = p; break; }
        if (target == null) {
            System.out.println("Patient not found.");
            HospitalSystem.pause();
            return;
        }

        while (true) {
            System.out.println("----------------------------------------");
            System.out.println(" Medical Records for " + target.getFullname() + " (ID: " + target.getPatientID() + ")");
            System.out.println("----------------------------------------");
            System.out.println("1. Add Record");
            System.out.println("2. Edit Record");
            System.out.println("3. Delete Record");
            System.out.println("4. Back");
            System.out.print("Enter choice: ");
            int c = HospitalSystem.getIntInput();

            switch (c) {
                case 1 -> {
                    System.out.print("Illness/Concern: ");
                    String illness = scanner.nextLine();
                    System.out.print("Treatment/Notes: ");
                    String treatment = scanner.nextLine();
                    target.addRecord(new MedicalRecord(illness, treatment));
                    System.out.println("Record added.");
                    HospitalSystem.pause();
                }
                case 2 -> {
                    if (target.getRecords().isEmpty()) { System.out.println("No records to edit."); HospitalSystem.pause(); break; }
                    for (int i = 0; i < target.getRecords().size(); i++) {
                        System.out.println((i+1) + ". " + target.getRecords().get(i));
                    }
                    System.out.print("Select record number to edit: ");
                    int idx = HospitalSystem.getIntInput();
                    if (idx < 1 || idx > target.getRecords().size()) { System.out.println("Invalid."); HospitalSystem.pause(); break; }
                    MedicalRecord rec = target.getRecords().get(idx-1);
                    System.out.print("New illness: ");
                    rec.setIllness(scanner.nextLine());
                    System.out.print("New treatment: ");
                    rec.setTreatment(scanner.nextLine());
                    System.out.println("Record updated.");
                    HospitalSystem.pause();
                }
                case 3 -> {
                    if (target.getRecords().isEmpty()) { System.out.println("No records to delete."); HospitalSystem.pause(); break; }
                    for (int i = 0; i < target.getRecords().size(); i++) {
                        System.out.println((i+1) + ". " + target.getRecords().get(i));
                    }
                    System.out.print("Select record number to delete: ");
                    int idx = HospitalSystem.getIntInput();
                    if (idx < 1 || idx > target.getRecords().size()) { System.out.println("Invalid."); HospitalSystem.pause(); break; }
                    System.out.print("Are you sure (Y/N): ");
                    String conf = scanner.nextLine();
                    if (conf.equalsIgnoreCase("Y")) {
                        target.getRecords().remove(idx-1);
                        System.out.println("Record deleted.");
                    } else System.out.println("Cancelled.");
                    HospitalSystem.pause();
                }
                case 4 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}