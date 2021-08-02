/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import Model.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 *  John Doe
 */
public class FileAccess {

    public FileAccess() {

        // Check if any of the .txts exists, if not create it
        // doubt this class can be cleaned up really
        String[] fileList = new String[4];
        fileList[0] = "Patients";
        fileList[1] = "Doctors";
        fileList[2] = "Registrations";
        fileList[3] = "CurrentUser";

        File tempFile;
        for (int i = 0; i < fileList.length; i++) {
            tempFile = new File(fileList[i] + ".txt");
            if (!tempFile.exists()) {
                writeToFile(fileList[i], "");
            }
        }
    }

    public void writeToFile(String fileName, String str) {
        try {
            FileWriter writer = new FileWriter(fileName + ".txt", true);
            writer.write(str);
            writer.close();
        } catch (IOException ioe) {
        }
    }

    // read all patients
    public ArrayList<Patient> readPatients() {
        ArrayList<Patient> allPatients = new ArrayList();
        String line;
        String patientinfo[];

        try {
            FileInputStream fs = new FileInputStream("Patients.txt");
            InputStreamReader ir = new InputStreamReader(fs);
            BufferedReader in = new BufferedReader(ir);

            while (in.ready()) {
                line = in.readLine();
                patientinfo = line.split("\t");

                Patient p = new Patient(patientinfo[0], patientinfo[1], patientinfo[2], patientinfo[3], Integer.parseInt(patientinfo[4]));

                if (patientinfo[6].equals("Vaccinated")) {
                    p.setVaccinated(2);
                }
                if (patientinfo[6].equals("Approved")) {
                    p.setVaccinated(1);
                }

                p.setDoctor(patientinfo[5]);

                allPatients.add(p);
            }

            in.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("readPatients IndexOutOfBounds:  No input found");;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allPatients;
    }

    // read all doctors
    public ArrayList<Doctor> readDoctors() {
        ArrayList<Doctor> allDoctors = new ArrayList();
        String line;
        String doctorinfo[];

        try {
            FileInputStream fs = new FileInputStream("Doctors.txt");
            InputStreamReader ir = new InputStreamReader(fs);
            BufferedReader in = new BufferedReader(ir);

            while (in.ready()) {
                line = in.readLine();
                doctorinfo = line.split("\t");

                Doctor d = new Doctor(doctorinfo[0], doctorinfo[1], doctorinfo[2]);

                allDoctors.add(d);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("readDoctors IndexOutOfBounds: No Input found");
        }
        return allDoctors;
    }

    // read all registrations
    public ArrayList<Registration> readReg() {
        ArrayList<Registration> allreg = new ArrayList();
        String line;
        String reginfo[];

        try {
            FileInputStream fs = new FileInputStream("Registrations.txt");
            InputStreamReader ir = new InputStreamReader(fs);
            BufferedReader in = new BufferedReader(ir);

            while (in.ready()) {
                line = in.readLine();
                reginfo = line.split("\t");

                LocalDate tempdate = LocalDate.parse(reginfo[0]);
                Registration r = new Registration(tempdate, reginfo[1]);

                allreg.add(r);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("readreg IndexOutOfBounds: No input found");
        }
        return allreg;
    }

    public Patient readCurrentUserPatient() {
        String line;
        String userinfo[];
        Patient tempPatient = null;

        try {
            FileInputStream fs = new FileInputStream("CurrentUser.txt");
            InputStreamReader ir = new InputStreamReader(fs);
            BufferedReader in = new BufferedReader(ir);

            // read one line
            in.ready();
            line = in.readLine();
            userinfo = line.split("\t");
            in.close();

            tempPatient = new Patient(userinfo[0], userinfo[1], userinfo[2], userinfo[3], Integer.parseInt(userinfo[4]));

            if (userinfo[6].equals("Vaccinated")) {
                tempPatient.setVaccinated(2);
            }
            if (userinfo[6].equals("Approved")) {
                tempPatient.setVaccinated(1);
            }

            tempPatient.setDoctor(userinfo[5]);

        } catch (FileNotFoundException e) {
            System.out.println("readCurrentUserPatient FileNotFound: File not found");
        } catch (IOException e) {
            System.out.println("readCurrentUserPatient IOException: Check in.readLine()");
        }
        return tempPatient;
    }

    public Doctor readCurrentUserDoctor() {

        String line;
        String doctorinfo[];
        Doctor tempDoctor = null;

        try {
            FileInputStream fs = new FileInputStream("CurrentUser.txt");
            InputStreamReader ir = new InputStreamReader(fs);
            BufferedReader in = new BufferedReader(ir);

            // read one line
            in.ready();
            line = in.readLine();
            doctorinfo = line.split("\t");
            in.close();

            tempDoctor = new Doctor(doctorinfo[0], doctorinfo[1], doctorinfo[2]);

        } catch (FileNotFoundException e) {
            System.out.println("readCurrentUserDoctor FileNotFound: File not found");
        } catch (IOException e) {
            System.out.println("readCurrentUserDoctor IOException: Check in.readLine()");
        }

        return tempDoctor;
    }

    public void resetfile(String fileName) {
        try {

            FileWriter fwOb = new FileWriter(fileName + ".txt", false);
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();

        } catch (Exception e) {
            System.out.println("resetfile Error Occured");
        }
    }

}
