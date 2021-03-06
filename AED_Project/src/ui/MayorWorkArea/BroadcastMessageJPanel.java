/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.MayorWorkArea;

import business.Enterprise.CityCommunities;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.CommunityOrganization;
import business.Organization.Organization;
import business.Person.Person;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author raunak
 */
public class BroadcastMessageJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    //private Enterprise enterprise;
    //private UserAccount userAccount;
    //private Person person;
    private Network n;

    //private RequestsToEPAJPanel jp;
    /**
     * Creates new form RequestLabTestJPanel
     */
    public BroadcastMessageJPanel(JPanel userProcessContainer, Network n) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.n = n;
        //this.person = userAccount.getPerson();

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

        btnSendMail = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        backJButton = new javax.swing.JButton();
        enterpriseLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMailBody = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        txtSubject = new javax.swing.JTextField();

        setBackground(new java.awt.Color(204, 204, 204));
        setBorder(new javax.swing.border.MatteBorder(null));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnSendMail.setBackground(new java.awt.Color(153, 153, 153));
        btnSendMail.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        btnSendMail.setText("Send");
        btnSendMail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendMailActionPerformed(evt);
            }
        });
        add(btnSendMail, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 330, -1, -1));

        jLabel1.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel1.setText("Message");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, -1, -1));

        backJButton.setBackground(new java.awt.Color(153, 153, 153));
        backJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton.setText("<<Back");
        backJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButtonActionPerformed(evt);
            }
        });
        add(backJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));

        enterpriseLabel.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        enterpriseLabel.setText("Broadcast CityWide Message");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 280, 30));

        txtMailBody.setColumns(20);
        txtMailBody.setRows(5);
        jScrollPane1.setViewportView(txtMailBody);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 110, 520, 200));

        jLabel2.setFont(new java.awt.Font("New Peninim MT", 1, 14)); // NOI18N
        jLabel2.setText("Subject");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));
        add(txtSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 70, 520, -1));
    }// </editor-fold>//GEN-END:initComponents


    private void btnSendMailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendMailActionPerformed

        ArrayList<String> recipients = new ArrayList<>();
        String ID;
        for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof CityCommunities) {
                for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                    if (o instanceof CommunityOrganization) {
                        for (Person p : o.getPersonDirectory().getPersonList()) {
                            if (p.geteMailID() != null) {
                                recipients.add(p.geteMailID());
                                //ID = p.geteMailID();
                            }
                        }
                    }
                }
            }
        }
        try {
            if (recipients.size() > 0) {
                String mailBody = txtMailBody.getText();
                String sub = txtSubject.getText();
                if (mailBody.trim().isEmpty() || sub.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Empty Mail or Subject not allowed!");
                    return;
                }

                javax.mail.internet.InternetAddress[] addressTo = new javax.mail.internet.InternetAddress[recipients.size()];

                for (int i = 0; i < recipients.size(); i++) {
                    try {
                        addressTo[i] = new javax.mail.internet.InternetAddress(recipients.get(i));
                    } catch (AddressException ex) {
                        Logger.getLogger(BroadcastMessageJPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                //String[] to = {recipients.get(0),"test2@yahoo.in","test3@gmail.com","test4@gmail.com"};
                Properties props = new Properties();

                props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.port", "587"); //TLS Port
                props.put("mail.smtp.auth", "true"); //enable authentication
                props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

                Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                return new PasswordAuthentication("ngaedproject2015@gmail.com", "ngaedproject");
                            }
                        });

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("ngaedproject2015@gmail.com"));
                    message.addRecipients(Message.RecipientType.BCC, addressTo);
                    message.setSubject(sub);
                    message.setText(mailBody);
                    Transport.send(message);
                    JOptionPane.showMessageDialog(null, "Message sent!");
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            } else {
                JOptionPane.showMessageDialog(null, "None of the resident E-Mail ID's present in system!");
                return;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Empty Mail or Subject not allowed!");
        }

    }//GEN-LAST:event_btnSendMailActionPerformed

    private void backJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButtonActionPerformed
        //jp.populateTable();
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);

    }//GEN-LAST:event_backJButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton;
    private javax.swing.JButton btnSendMail;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtMailBody;
    private javax.swing.JTextField txtSubject;
    // End of variables declaration//GEN-END:variables
}
