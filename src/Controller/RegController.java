/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Registration;
import java.util.ArrayList;

/**
 *
 * @author 
 */
public class RegController {

    FileAccess fa;

    public RegController() {
        fa = new FileAccess();
    }

    // get all registrations
    public ArrayList<Registration> getAllRegistrations() {
        ArrayList<Registration> allreg = new ArrayList<Registration>();
        allreg = fa.readReg();

        return allreg;
    }

    public void addRegistration(String name) {
        Registration r = new Registration(name);
        String str = r.toString() + "\n";
        fa.writeToFile("registrations", str);
    }

    public void resetFile(String filename) {
        fa.resetfile(filename);
    }

}
