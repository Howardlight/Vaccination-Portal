/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.DocController;
import Controller.PaController;
import Model.Doctor;
import Model.Patient;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author John Doe
 */
public class Admin_ApprovePatient extends javax.swing.JFrame {
    
    PaController paCont;
    DocController docCont;
    
    // contains the patients to be submitted for approval
    private ArrayList<Patient> toBeSubmitted = new ArrayList<Patient>();
    
    // contains the selected rows
    private int[] selection;
    
    
    public Admin_ApprovePatient() {
        initComponents();
        paCont = new PaController();
        docCont = new DocController();
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("Admin Panel || Approve Patients");
        
        
        Submit.setEnabled(false);
        ErrorLabel.setEnabled(false);
        
        // PROCCESSING DATA FOR THE TABLE
        // KNOWN BUG //
        // you can move the headers along with the data inside it
        // if you unapprove or undo changes, and the table headers have been changed
        // the last 2 headers will be changed, no matter if it was vaccination or
        // doctor or anything else
        try{
            // Create the columns
            String[] columns = new String[6];
            columns[0] = "Name";
            columns[1] = "City";
            columns[2] = "Occupation";
            columns[3] = "Age";
            columns[4] = "Doctor";
            columns[5] = "Vaccination";


            // Process the Array of Arrays
            ArrayList<Patient> allpatients = paCont.getAllPatients();

            // turn each Obj into an array of data(Strings)
            String[][] allpatientsArray = new String[allpatients.size()][6];

            
            // variables used for passing data around
            String tempString;
            String tempStringList[] = new String[7];

            // this effectively processes the data to be passed to the table
            for(int i = 0; i < allpatients.size(); i++){

                // get toString, Split it using \t, then pass them into an array
                tempString = allpatients.get(i).toString();

                // Composition of List = [NAME, PASSWORD, CITY, OCC, AGE, DOCTOR, VACC]
                // Table Layout = [NAME, CITY, OCC, AGE, DOCTOR, VACC]
                // so: 0 to 0, 1 to null, 2 to 1, 3 to 2, 4 to 3, 5 to 4, 6 to 5
                tempStringList = tempString.split("\t");
                
                
                // if patient is vaccinated or non approved, skip
                // TODO: MAKE THIS A METHOD OR CLASS
                System.out.println(tempStringList[6]);
                if(tempStringList[6].equals("Vaccinated") 
                        || tempStringList[6].equals("Approved")){
                    continue;
                }
                
                // Restructuring the data
                allpatientsArray[i][0] = tempStringList[0];
                allpatientsArray[i][1] = tempStringList[2];
                allpatientsArray[i][2] = tempStringList[3];
                allpatientsArray[i][3] = tempStringList[4];
                allpatientsArray[i][4] = tempStringList[5];
                allpatientsArray[i][5] = tempStringList[6];
                    
                
            }
            
            // feed data to the table
            PatientTable.setModel(
                    // DefaultTableModel modified so CELLS CANNOT BE EDITED
                    new javax.swing.table.DefaultTableModel(allpatientsArray, columns){

                        @Override
                        public boolean isCellEditable(int row, int column) {
                           //all cells false
                           return false;
                        }
                    }
                );
            
        } catch(IndexOutOfBoundsException e){
            System.out.println("Table Data Processing IndexOutOfBounds");
            System.out.println("Closing Admin_PatientInfo");
            
            // closes the frame
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
        // Create an auto sorter then add it to the Table
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(PatientTable.getModel());
        PatientTable.setRowSorter(sorter);
        
        // makes it so you can select multiple Patients
        PatientTable.setRowSelectionAllowed(true);
        PatientTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        // FILLING COMBOBOX
        ArrayList<Doctor> alldoctors = new ArrayList<Doctor>();
        alldoctors = docCont.getAllDoctors();
        for(int i = 0; i < alldoctors.size(); i++){
            DoctorComboBox.addItem(alldoctors.get(i).getName());
            alldoctors.get(i).getName();
        }
        
        
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        PatientTable = new javax.swing.JTable();
        ApproveSelected = new javax.swing.JButton();
        Submit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        DoctorComboBox = new javax.swing.JComboBox<>();
        UnapproveSelected = new javax.swing.JButton();
        ErrorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        PatientTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(PatientTable);

        ApproveSelected.setText("Approved Selected");
        ApproveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ApproveSelectedActionPerformed(evt);
            }
        });

        Submit.setText("Submit Changes");
        Submit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SubmitActionPerformed(evt);
            }
        });

        jLabel1.setText("Selected Doctor:");

        UnapproveSelected.setText("Unapprove Selected");
        UnapproveSelected.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UnapproveSelectedActionPerformed(evt);
            }
        });

        ErrorLabel.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        ErrorLabel.setForeground(new java.awt.Color(255, 0, 0));
        ErrorLabel.setText("Invalid Patient");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(DoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(UnapproveSelected)
                        .addGap(18, 18, 18)
                        .addComponent(ApproveSelected)
                        .addGap(18, 18, 18)
                        .addComponent(Submit, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(ErrorLabel)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ApproveSelected, Submit, UnapproveSelected});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 267, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(ErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ApproveSelected)
                    .addComponent(jLabel1)
                    .addComponent(DoctorComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Submit)
                    .addComponent(UnapproveSelected))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ApproveSelected, Submit, UnapproveSelected});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ApproveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ApproveSelectedActionPerformed
        
        // grab the seleced (by mouse) rows 
        selection = PatientTable.getSelectedRows();
        
        
        try{
            
            String name;
            String city;
            String doctor = DoctorComboBox.getSelectedItem().toString();
            Patient tempPatient;
            for(int i = 0; i < (selection.length); i++){
                // find patient by index, feed its data to as obj to toBeSubmitted
                // shadow change the Vaccinated column

                // To get patient use cont.getPatient() using Vars NAME and CITY
                name = (String)PatientTable.getValueAt(selection[i], 0);
                city = (String)PatientTable.getValueAt(selection[i], 1);

                // get Patient Obj then pass it to the array
                tempPatient = paCont.getPatient(name, city);
                tempPatient.setVaccinated(1);
                tempPatient.setDoctor(doctor);
                toBeSubmitted.add(tempPatient);


                // Shadow Change the Table
                PatientTable.setValueAt(doctor, selection[i], 4);
                PatientTable.setValueAt("Approved", selection[i], 5);
            }




            // disable components
            ApproveSelected.setEnabled(false);
            PatientTable.getTableHeader().setEnabled(false);
            Submit.setEnabled(true);
        }catch(NullPointerException e){
            ErrorLabel.setEnabled(true);
        }
    }//GEN-LAST:event_ApproveSelectedActionPerformed

    private void UnapproveSelectedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UnapproveSelectedActionPerformed
    
        // loops over the table and resets all changed values
        for(int i = 0; i < selection.length; i++){
            PatientTable.setValueAt("Unspecified", selection[i], 4);
            PatientTable.setValueAt("Unvaccinated", selection[i], 5);
        }
        
        // clear the array
        toBeSubmitted.clear();
        
        // re-enable components
        ApproveSelected.setEnabled(true);
        PatientTable.getTableHeader().setEnabled(true);
        Submit.setEnabled(false);
        ErrorLabel.setEnabled(false);
    }//GEN-LAST:event_UnapproveSelectedActionPerformed

    private void SubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SubmitActionPerformed
        
        
        // compare patients, if patient matches with one in toBesubmitted skip on it
        // or replace it
        ArrayList<Patient> allpatients = paCont.getAllPatients();
        
        for(int i = 0; i < allpatients.size(); i++){
            
            for(int j = 0; j < toBeSubmitted.size(); j++){
                
                // compares patients in all patients to each one in toBeSubmitted
                innerloop:
                if(allpatients.get(i).getName().toLowerCase().equals(toBeSubmitted.get(j).getName().toLowerCase()) &&
                        allpatients.get(i).getCity().toLowerCase().equals(toBeSubmitted.get(j).getCity().toLowerCase())){
                    
                    allpatients.get(i).setDoctor(toBeSubmitted.get(j).getDoctor());
                    allpatients.get(i).setVaccinated(1);
                    break innerloop;
                }    
                
                
            }
        }
        
        // grab the array, reset the txt file, then rewrite it
        paCont.resetFile("Patients");
        
        for(int i = 0; i < allpatients.size(); i++){
            paCont.addPatient(allpatients.get(i));
        }
        
        
        ApproveSelected.setEnabled(true);
        PatientTable.getTableHeader().setEnabled(true);
        Submit.setEnabled(false);
        ErrorLabel.setEnabled(false);
    }//GEN-LAST:event_SubmitActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Admin_ApprovePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Admin_ApprovePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Admin_ApprovePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Admin_ApprovePatient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Admin_ApprovePatient().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ApproveSelected;
    private javax.swing.JComboBox<String> DoctorComboBox;
    private javax.swing.JLabel ErrorLabel;
    private javax.swing.JTable PatientTable;
    private javax.swing.JButton Submit;
    private javax.swing.JButton UnapproveSelected;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
