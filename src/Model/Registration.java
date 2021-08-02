/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.time.LocalDate;

/**
 *
 * @author
 */
public class Registration {
    
    private LocalDate regdate;
    private String patient_name;
    
    public Registration(String patient_name) {
        this.regdate = LocalDate.now();
        this.patient_name = patient_name;
    }
    
    public Registration(LocalDate regdate, String patient_name) {
        this.regdate = regdate;
        this.patient_name = patient_name;
    }
    
    
    public LocalDate getRegdate() {
        return regdate;
    }   
    public String getPatient_name() {
        return patient_name;
    }

    
    public void setRegdate(LocalDate regdate) {
        this.regdate = regdate;
    }
    public void setPatient_name(String patient_name) {
        this.patient_name = patient_name;
    }

    @Override
    public String toString() {
        String out="";
        out += regdate + "\t" ;
        out += patient_name;
        
        return out;
    }
    
    
    
    
}
