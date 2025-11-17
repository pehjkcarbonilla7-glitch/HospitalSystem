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

public class PriorityNumber {
     private final String priorityNumber;
    private final String patientName;
    private final int type; // 1 normal, 2 emergency

    public PriorityNumber(String priorityNumber, String patientName, int type) {
        this.priorityNumber = priorityNumber;
        this.patientName = patientName;
        this.type = type;
    }

    public String getPriorityNumber() { return priorityNumber; }
    public String getPatientName() { return patientName; }
    public int getType() { return type; }
}