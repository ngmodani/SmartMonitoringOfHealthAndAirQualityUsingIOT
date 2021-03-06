/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ResidentWork;

import business.Community.Resident;
import business.Health.Disease;
import business.Health.DiseaseTypes;
import business.Verifiers.MyStringVerifier;

import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author kedarvdm
 */
public class AddHealthRecord extends javax.swing.JPanel {

    /**
     * Creates new form CreateVitalSignJPanel
     */
    private Resident patient;
    private JPanel userProcessContainer;
    private ManageHealthJPanel mhp;

    public AddHealthRecord(JPanel upc, Resident patient, ManageHealthJPanel mhp) {
        initComponents();
        this.userProcessContainer = upc;
        this.patient = patient;
        this.mhp = mhp;
        populateComboBox();
    }

    public void populateComboBox() {
        diseaseJComboBox.removeAllItems();

        diseaseJComboBox.addItem(DiseaseTypes.getInstance().getType1());
        diseaseJComboBox.addItem(DiseaseTypes.getInstance().getType2());
        diseaseJComboBox.addItem(DiseaseTypes.getInstance().getType3());
        diseaseJComboBox.addItem(DiseaseTypes.getInstance().getType4());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        saveJButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        backJButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        diseaseJComboBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(245, 153, 0));
        setMinimumSize(new java.awt.Dimension(500, 700));
        setPreferredSize(new java.awt.Dimension(500, 700));

        jLabel1.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel1.setText("Description");

        saveJButton.setBackground(new java.awt.Color(153, 153, 153));
        saveJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        saveJButton.setText("Save");
        saveJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveJButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Add Health Record");

        backJButton1.setBackground(new java.awt.Color(153, 153, 153));
        backJButton1.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton1.setText("<<Back");
        backJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton1ActionPerformed(evt);
            }
        });

        jDateChooser1.setBackground(new java.awt.Color(153, 153, 153));
        jDateChooser1.setDateFormatString("MM/dd/yyyy");

        jLabel2.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel2.setText("Date");

        diseaseJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                                .addComponent(saveJButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(101, 101, 101))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(diseaseJComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(backJButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(diseaseJComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(71, 71, 71)
                .addComponent(saveJButton)
                .addGap(174, 174, 174)
                .addComponent(backJButton1)
                .addContainerGap(282, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void saveJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveJButtonActionPerformed
        // TODO add your handling code here:
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String issue = (String)diseaseJComboBox.getSelectedItem();
        try {
            Date date = jDateChooser1.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Calendar beginCalendar = Calendar.getInstance();
            beginCalendar.setTime(patient.getDOB());

            Calendar endCalendar = Calendar.getInstance();
            endCalendar.setTime(date);

            int minusDays = 0;       // for calculating no. of days
            while (true) {
                minusDays++;

                // Day increasing by 1
                beginCalendar.add(Calendar.DAY_OF_MONTH, 1);

                if (dateFormat.format(beginCalendar.getTime()).
                        equals(dateFormat.format(endCalendar.getTime()))) {
                    break;
                }
            }
            if (minusDays < 1) {
                JOptionPane.showMessageDialog(this, "Date must be greater than DOB!", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                Disease record = patient.getHealthRecord().createAndAddDisease();
                record.setDiseaseDescription(issue);
                record.setTimeOfDisease(date);

                JOptionPane.showMessageDialog(this, "Record added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Please Choose Date!!", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_saveJButtonActionPerformed
   
    private void backJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton1ActionPerformed
        // TODO add your handling code here:
        mhp.populateVitalSignTable();
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton1;
    private javax.swing.JComboBox diseaseJComboBox;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JButton saveJButton;
    // End of variables declaration//GEN-END:variables
}
