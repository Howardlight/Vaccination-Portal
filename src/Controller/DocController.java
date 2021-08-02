/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Doctor;
import java.util.ArrayList;

/**
 *
 * @author John Doe
 */
public class DocController {

    FileAccess fa;

    public DocController() {
        fa = new FileAccess();
    }

    // get all doctors
    public ArrayList<Doctor> getAllDoctors() {
        ArrayList<Doctor> alldoctors = new ArrayList<Doctor>();
        alldoctors = fa.readDoctors();

        return alldoctors;
    }

    public Doctor getDoctor(String name) {
        // get list of Patients
        ArrayList<Doctor> alldoctors = this.getAllDoctors();
        for (int i = 0; i < alldoctors.size(); i++) {

            if (alldoctors.get(i).getName().equals(name)) {
                return alldoctors.get(i);
            }

        }

        // if Doctor has not been found
        return null;
    }

    public void addDoctor(String name, String password, String hospital) {
        Doctor d = new Doctor(name, password, hospital);
        String str = d.toString() + "\n";
        fa.writeToFile("doctors", str);
    }

    public void setCurrentUserDoctor(String name) {
        Doctor tempDoctor = this.getDoctor(name);

        fa.resetfile("CurrentUser");
        fa.writeToFile("CurrentUser", tempDoctor.toString());
    }

    public Doctor getCurrentUserDoctor() {
        return fa.readCurrentUserDoctor();
    }

    public void resetFile(String filename) {
        fa.resetfile(filename);
    }

}
