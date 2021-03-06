/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.ResidentWork;

import business.Community.Resident;
import business.Enterprise.Enterprise;
import business.Enterprise.HospitalEnterprise;
import business.Health.MedicalHelpIssues;
import business.Network.Network;
import business.Organization.DoctorOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import business.WorkQueue.DocAdviceWorkRequest;
import java.awt.CardLayout;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class RequestDescriptionJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    //private Enterprise enterprise;
    private UserAccount userAccount;
    private Resident res;
    private Network net;

    /**
     * Creates new form RequestLabTestJPanel
     */
    public RequestDescriptionJPanel(JPanel userProcessContainer, UserAccount account, Network net) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.net = net;
        this.userAccount = account;
        this.res = userAccount.getResident();
        populateComboBox();
    }

    public void populateComboBox() {
        symptomJComboBox.removeAllItems();

        symptomJComboBox.addItem(MedicalHelpIssues.getInstance().getType1());
        symptomJComboBox.addItem(MedicalHelpIssues.getInstance().getType2());
        symptomJComboBox.addItem(MedicalHelpIssues.getInstance().getType3());
        symptomJComboBox.addItem(MedicalHelpIssues.getInstance().getType4());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        requestTestJButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        symptomJComboBox = new javax.swing.JComboBox();

        setBackground(new java.awt.Color(245, 153, 0));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requestTestJButton.setBackground(new java.awt.Color(153, 153, 153));
        requestTestJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        requestTestJButton.setText("Request");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });
        add(requestTestJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(215, 95, -1, -1));

        jLabel1.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel1.setText("Message");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 50, -1, -1));

        backJButton.setBackground(new java.awt.Color(153, 153, 153));
        backJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton.setText("<<Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 138, -1, -1));

        enterpriseLabel.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        enterpriseLabel.setText("Request Doctor Assisstance");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 280, 30));

        symptomJComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        add(symptomJComboBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 50, 210, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed

        String message = (String) symptomJComboBox.getSelectedItem();

        DocAdviceWorkRequest request = new DocAdviceWorkRequest();
        request.setMessage(message);
        request.setSender(userAccount);
        request.setStatus("Sent");

        Organization org = null;
        outerloop:
        for (Enterprise enterprise : net.getEnterpriseDirectory().getEnterpriseList()) {
            if (enterprise instanceof HospitalEnterprise) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof DoctorOrganization) {
                        org = organization;
                        break outerloop;
                    }
                }
            }
            if (org != null) {
                org.getWorkQueue().getWorkRequestList().add(request);
                userAccount.getWorkQueue().getWorkRequestList().add(request);
            }
        }
    }//GEN-LAST:event_requestTestJButtonActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed

        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);

    }//GEN-LAST:event_backJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JComboBox symptomJComboBox;
    // End of variables declaration//GEN-END:variables
}
