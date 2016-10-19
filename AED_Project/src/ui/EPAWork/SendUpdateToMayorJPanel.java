/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.EPAWork;

import business.Enterprise.CityCouncilEnterprise;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.MayorOrganization;
import business.Organization.Organization;
import business.Verifiers.MyStringVerifier;
import business.WorkQueue.DocAdviceWorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class SendUpdateToMayorJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    //private Enterprise enterprise;
    // private UserAccount userAccount;
    private Network n;

    // private RequestsToEPAJPanel jp;
    /**
     * Creates new form ProcessWorkRequestJPanel
     */
    public SendUpdateToMayorJPanel(JPanel userProcessContainer, Network n) {
        initComponents();
        this.userProcessContainer = userProcessContainer;
        this.n = n;
        //userAccount = account;
        MyStringVerifier my = new MyStringVerifier();
        resultJTextField.setInputVerifier(my);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        submitJButton = new javax.swing.JButton();
        resultJTextField = new javax.swing.JTextField();
        lblDay = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        backJButton1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(133, 210, 226));

        submitJButton.setBackground(new java.awt.Color(153, 153, 153));
        submitJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        submitJButton.setText("Send");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        lblDay.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        lblDay.setText("Today's Update to Mayor");

        jLabel2.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel2.setText("Message");

        backJButton1.setBackground(new java.awt.Color(153, 153, 153));
        backJButton1.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton1.setText("<<Back");
        backJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backJButton1)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(submitJButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(resultJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(lblDay)))
                        .addGap(0, 374, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblDay)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(52, 52, 52)
                .addComponent(submitJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(backJButton1)
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        String message = resultJTextField.getText();
        if (message.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Empty Update not Allowed!");
        }
        DocAdviceWorkRequest request = new DocAdviceWorkRequest();
        request.setMessage(message);
        //request.setSender(userAccount);
        request.setStatus("Environment Department");

        Organization org = null;
        outerloop:
        for (Enterprise enterprise : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (enterprise instanceof CityCouncilEnterprise) {
                for (Organization organization : enterprise.getOrganizationDirectory().getOrganizationList()) {
                    if (organization instanceof MayorOrganization) {
                        org = organization;
                        break outerloop;
                    }
                }
            }
        }
        if (org != null) {
            org.getWorkQueue().getWorkRequestList().add(request);
            //userAccount.getWorkQueue().getWorkRequestList().add(request);

        }

    }//GEN-LAST:event_submitJButtonActionPerformed

    private void backJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton1ActionPerformed

        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblDay;
    private javax.swing.JTextField resultJTextField;
    private javax.swing.JButton submitJButton;
    // End of variables declaration//GEN-END:variables
}