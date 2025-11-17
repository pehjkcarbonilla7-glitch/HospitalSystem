/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystem;

/**
 *
 * @author Jaycee Kristine
 */
public class HospitalCounter {
     private static int normalCounter = 1;
    private static int emergencyCounter = 1;

    public static int nextNormal() { return normalCounter++; }
    public static int nextEmergency() { return emergencyCounter++; }
}
