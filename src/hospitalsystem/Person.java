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
public class Person {
   private final String patientID;
    private String firstname;
    private String middlename;
    private String lastname;
    private String sex;
    private int age;
    private String birthdate;
    private String birthplace;
    private String contactNumber;
    private String civilStatus;
    private final List<MedicalRecord> records = new ArrayList<>();

    public Person(String patientID, String firstname, String middlename, String lastname,
                  String sex, int age, String birthdate, String birthplace,
                  String contactNumber, String civilStatus) {
        this.patientID = patientID;
        this.firstname = firstname;
        this.middlename = middlename;
        this.lastname = lastname;
        this.sex = sex;
        this.age = age;
        this.birthdate = birthdate;
        this.birthplace = birthplace;
        this.contactNumber = contactNumber;
        this.civilStatus = civilStatus;
    }

    // getters and setters used by PatientManagement
    public String getPatientID() { return patientID; }
    public String getFirstname() { return firstname; }
    public String getMiddlename() { return middlename; }
    public String getLastname() { return lastname; }
    public String getSex() { return sex; }
    public int getAge() { return age; }
    public String getBirthdate() { return birthdate; }
    public String getBirthplace() { return birthplace; }
    public String getContactNumber() { return contactNumber; }
    public String getCivilStatus() { return civilStatus; }
    public List<MedicalRecord> getRecords() { return records; }

    public String getFullname() {
        return firstname + " " + (middlename == null || middlename.isBlank() ? "" : (middlename + " ")) + lastname;
    }

    public void setFirstname(String v) { this.firstname = v; }
    public void setMiddlename(String v) { this.middlename = v; }
    public void setLastname(String v) { this.lastname = v; }
    public void setSex(String v) { this.sex = v; }
    public void setAge(int v) { this.age = v; }
    public void setBirthdate(String v) { this.birthdate = v; }
    public void setBirthplace(String v) { this.birthplace = v; }
    public void setContactNumber(String v) { this.contactNumber = v; }
    public void setCivilStatus(String v) { this.civilStatus = v; }

    public void addRecord(MedicalRecord r) { records.add(r); }

    @Override
    public String toString() {
        return String.format("%s | %s | %s | %s | %d | %s | %s | %s | %s | %d",
                firstname, lastname, middlename, sex, age, birthdate, birthplace, contactNumber, civilStatus, records.size());
    }
}