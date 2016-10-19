/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.HealthDeptWork;

import business.Enterprise.CityCouncilEnterprise;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.EnvironmentDeptOrganization;
import business.Organization.Organization;
import business.Person.Person;
import business.UserAccount.UserAccount;
import business.Verifiers.MyStringVerifier;
import business.WorkQueue.DocAdviceWorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class RequestAssistanceJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    //private Enterprise enterprise;
    private UserAccount userAccount;
    private Person person;
    private Network n;
    private RequestsToEPAJPanel jp;

    /**
     * Creates new form RequestLabTestJPanel
     */
    public RequestAssistanceJPanel(JPanel userProcessContainer, Network n, UserAccount account, RequestsToEPAJPanel jp) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.jp = jp;
        this.n = n;
        this.person = userAccount.getPerson();
        MyStringVerifier my = new MyStringVerifier();
        txtMessage.setInputVerifier(my);
        //populateComboBox();
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
        txtMessage = new javax.swing.JTextField();

        setBackground(new java.awt.Color(52, 169, 66));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        requestTestJButton.setBackground(new java.awt.Color(153, 153, 153));
        requestTestJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        requestTestJButton.setText("Request");
        requestTestJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                requestTestJButtonActionPerformed(evt);
            }
        });
        add(requestTestJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, -1, -1));

        jLabel1.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel1.setText("Message");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, -1, -1));

        backJButton.setBackground(new java.awt.Color(153, 153, 153));
        backJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton.setText("<<Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        enterpriseLabel.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        enterpriseLabel.setText("Request EPA Assisstance");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 280, 30));
        add(txtMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 70, 360, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void requestTestJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_requestTestJButtonActionPerformed

        String message = txtMessage.getText();
        if (message.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty Fields not allowed!");
            return;
        }
        DocAdviceWorkRequest request = new DocAdviceWorkRequest();
        request.setMessage(message);
        request.setSender(userAccount);
        request.setStatus("Sent");

        Organization org = null;
        outerloop:
        for (Enterprise enterprise : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (enterprise instanceof CityCouncilEnterprise) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof EnvironmentDeptOrganization) {
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
        jp.populateTable();
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);

    }//GEN-LAST:event_backJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton requestTestJButton;
    private javax.swing.JTextField txtMessage;
    // End of variables declaration//GEN-END:variables
}
