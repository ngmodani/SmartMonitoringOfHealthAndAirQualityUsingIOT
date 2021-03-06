/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.HealthDeptWork;

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
    //private UserAccount userAccount;
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
        jLabel1 = new javax.swing.JLabel();
        resultJTextField = new javax.swing.JTextField();
        backJButton = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();

        setBackground(new java.awt.Color(52, 169, 66));

        submitJButton.setBackground(new java.awt.Color(153, 153, 153));
        submitJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        submitJButton.setText("Send");
        submitJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitJButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel1.setText("Message");

        backJButton.setBackground(new java.awt.Color(153, 153, 153));
        backJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton.setText("<<Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });

        lblDay.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        lblDay.setText("Today's Update to Mayor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(37, 37, 37)
                                .addComponent(resultJTextField))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 273, Short.MAX_VALUE)
                                .addComponent(submitJButton)))
                        .addGap(63, 63, 63))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backJButton)
                            .addComponent(lblDay))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblDay)
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(resultJTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(submitJButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                .addComponent(backJButton)
                .addGap(53, 53, 53))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed

        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButtonActionPerformed

    private void submitJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitJButtonActionPerformed
        String message = resultJTextField.getText();
        if(message.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "Empty Fields Not allowed!");
            return;
        }
        DocAdviceWorkRequest request = new DocAdviceWorkRequest();
        request.setMessage(message);
        //request.setSender(userAccount);
        request.setStatus("Health Department");

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblDay;
    private javax.swing.JTextField resultJTextField;
    private javax.swing.JButton submitJButton;
    // End of variables declaration//GEN-END:variables
}
