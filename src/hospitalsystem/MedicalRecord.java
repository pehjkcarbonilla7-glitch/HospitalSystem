/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hospitalsystem;

/**
 *
 * @author Jaycee Kristine
 */
import java.time.LocalDate;

public class MedicalRecord {
    private final LocalDate date;
    private String illness;
    private String treatment;

    public MedicalRecord(String illness, String treatment) {
        this.date = LocalDate.now();
        this.illness = illness;
        this.treatment = treatment;
    }

    public LocalDate getDate() { return date; }
    public String getIllness() { return illness; }
    public String getTreatment() { return treatment; }

    public void setIllness(String v) { this.illness = v; }
    public void setTreatment(String v) { this.treatment = v; }

    @Override
    public String toString() {
        return date + " - " + illness + " | " + treatment;
    }
}