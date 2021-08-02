/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author
 */
public class Patient {
    
    // all variables
    private String name;
    private String password;
    private String city;
    private String occupation;
    private String doctor;
    private int age;
    private String vaccinated;
    
    
    // Constructors
    public Patient() {
        this.name = "Undefined";
        this.city = "Unspecified";
        this.occupation = "Unspecified";
        this.age = 0;
        this.doctor = "Unspecified";
        this.vaccinated = "Unvaccinated";
    }
    
    
    public Patient(String name, String password, String city, String occupation, int age) {
        this.name = name;
        this.password = password;
        this.city = city;
        this.occupation = occupation;
        this.age = age;
        this.doctor = "Unspecified";
        this.vaccinated = "Unvaccinated";
    }
    
    
    public void Vaccinate(){
        vaccinated = "Vaccinated";
    }
    
    // Getters
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getCity() {
        return city;
    }
    public String getOccupation() {
        return occupation;
    }
    public int getAge() {
        return age;
    }
    public String getVaccinated() {
        return vaccinated;
    }
    public String getDoctor() {
        return doctor;
    }
    

    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setVaccinated(int vaccinated) {
        
        // 1 FOR APPROVED, 2 FOR VACCINATED
        if(vaccinated == 1) this.vaccinated = "Approved";
        else if(vaccinated == 2) this.vaccinated = "Vaccinated";
        else this.vaccinated = this.vaccinated;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    
    
    
    
    @Override
    public String toString() {
        String out = "";
        
        out += name + "\t";
        out += password + "\t";
        out += city + "\t";
        out += occupation + "\t";
        out += age + "\t";
        out += doctor + "\t";
        out += vaccinated;
        
        return out;
    }
}
