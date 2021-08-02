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
public class Doctor {
    
    private String name;
    private String password;
    private String hospital;

    // Constructors
    public Doctor(String name, String password, String hospital) {
        this.name = name;
        this.password = password;
        this.hospital = hospital;
    }
    
    
    // Getters
    public String getName() {
        return name;
    }
    public String getPassword() {
        return password;
    }
    public String getHospital() {
        return hospital;
    }

    
    // Setters
    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    @Override
    public String toString() {
        String out = "";
        out += name + "\t";
        out += password + "\t";
        out += hospital;
        
        return out;
    }
    
    
    
}
