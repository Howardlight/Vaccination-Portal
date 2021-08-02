/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Patient;
import java.util.ArrayList;

/**
 *
 * @author John Doe
 */
public class PaController {

    FileAccess fa;

    public PaController() {
        fa = new FileAccess();
    }

    public ArrayList<Patient> getAllPatients() {
        ArrayList<Patient> allpatients = new ArrayList<Patient>();
        allpatients = fa.readPatients();

        return allpatients;
    }

    // get patients sorted by which doctor
    public ArrayList<Patient> getPatientsByDoctor(String name) {
        ArrayList<Patient> patientsByDoctor = new ArrayList<Patient>();
        ArrayList<Patient> allpatients = new ArrayList<Patient>();
        allpatients = fa.readPatients();
        for (int i = 0; i < allpatients.size(); i++) {
            if (allpatients.get(i).getDoctor().equals(name)) {
                patientsByDoctor.add(allpatients.get(i));
            }
        }

        return patientsByDoctor;
    }

    // get patients sorted by which city
    public ArrayList<Patient> getPatientsByCity(String city) {
        ArrayList<Patient> patientsByCity = new ArrayList<Patient>();
        ArrayList<Patient> allpatients = new ArrayList<Patient>();
        allpatients = fa.readPatients();
        for (int i = 0; i < allpatients.size(); i++) {
            if (allpatients.get(i).getCity().equals(city)) {
                patientsByCity.add(allpatients.get(i));
            }
        }

        return patientsByCity;
    }

    public ArrayList<Patient> getUnvaccPatients() {
        ArrayList<Patient> UnvaccPatients = new ArrayList<Patient>();
        ArrayList<Patient> allpatients = new ArrayList<Patient>();
        allpatients = fa.readPatients();
        for (int i = 0; i < allpatients.size(); i++) {
            if (allpatients.get(i).getVaccinated().equals("Unvaccinated")
                    || allpatients.get(i).getVaccinated().equals("Approved")) {
                UnvaccPatients.add(allpatients.get(i));
            }
        }

        return UnvaccPatients;
    }

    public ArrayList<Patient> getPendingPatients() {
        ArrayList<Patient> PendingPatients = new ArrayList<Patient>();
        ArrayList<Patient> allpatients = new ArrayList<Patient>();
        allpatients = fa.readPatients();
        for (int i = 0; i < allpatients.size(); i++) {
            if (allpatients.get(i).getVaccinated().equals("Unvaccinated")) {
                PendingPatients.add(allpatients.get(i));
            }
        }

        return PendingPatients;
    }

    public Patient getPatient(String name, String city) {
        // get list of Patients
        ArrayList<Patient> allpatients = this.getAllPatients();
        for (int i = 0; i < allpatients.size(); i++) {

            if (allpatients.get(i).getName().equals(name) && allpatients.get(i).getCity().equals(city)) {

                if (allpatients.get(i).getVaccinated().equals("Vaccinated")) {
                    allpatients.get(i).setVaccinated(2);
                }
                if (allpatients.get(i).getVaccinated().equals("Approved")) {
                    allpatients.get(i).setVaccinated(1);
                }

                return allpatients.get(i);
            }

        }

        // if Patient has not been found
        return null;
    }

    // add patient, registration, doctor....
    public void addPatient(String name, String password, String city, String occupation, int age) {
        Patient tempPatient = new Patient(name, password, city, occupation, age);
        String str = tempPatient.toString() + "\n";
        fa.writeToFile("Patients", str);
    }

    public void addPatient(String name, String password, String city, String occupation, int age, String doctor, String vaccinated) {
        Patient tempPatient = new Patient(name, password, city, occupation, age);

        if (vaccinated.equals("Vaccinated")) {
            tempPatient.setVaccinated(2);
        }
        if (vaccinated.equals("Approved")) {
            tempPatient.setVaccinated(1);
        }
        tempPatient.setDoctor(doctor);

        String str = tempPatient.toString() + "\n";
        fa.writeToFile("Patients", str);
    }

    public void addPatient(Patient patient) {
        String str = patient.toString() + "\n";
        fa.writeToFile("Patients", str);
    }

    public void setCurrentUserPatient(String name, String city) {
        Patient tempPatient = this.getPatient(name, city);

        // makes sure the old user has been deleted, then toString()s the new one
        fa.resetfile("CurrentUser");
        fa.writeToFile("CurrentUser", tempPatient.toString());
    }

    public Patient getCurrentUserPatient() {
        return fa.readCurrentUserPatient();
    }

    public void resetFile(String filename) {
        fa.resetfile(filename);
    }

}
