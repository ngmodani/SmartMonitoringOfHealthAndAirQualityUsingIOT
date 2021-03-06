/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.EPAWork;

import business.Enterprise.CityCommunities;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.CommunityOrganization;
import business.Organization.Organization;
import business.Sensor.Sensor;
import business.Sensor.SensorData;
import java.awt.CardLayout;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class AirQualityStatisticsJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Enterprise e;
    private Network n;
    //private UserAccount userAccount;
    //private DoctorOrganization docOrganization;
    private Date currDate;

    /**
     * Creates new form LabAssistantWorkAreaJPanel
     */
    public AirQualityStatisticsJPanel(JPanel userProcessContainer, Network n) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        // this.userAccount = account;
        this.n = n;
        currDate = new Date();
        populateTable(currDate);

    }

    public void populateTable(Date date) {
        DefaultTableModel model = (DefaultTableModel) environmentConditionsJTable.getModel();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int AQI = 0;
        int temp = 0;
        int count = 0;
        model.setRowCount(0);

        for (Enterprise ep : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (ep instanceof CityCommunities) {
                for (Organization o : ep.getOrganizationDirectory().getOrganizationList()) {
                    if (o instanceof CommunityOrganization) {

                        temp = 0;           //initializing temp to 0 for each Community
                        AQI = 0;            //initializing AQI to 0 for each Community
                        count = 0;          //counter to take count of Readings to find Avg.
                        for (Sensor s : o.getSensorList().getList()) {
                            for (SensorData sd : s.getSensorDataRecord()) {
                                if (df.format(date).equals(df.format(sd.getTimestamp()))) {
                                    AQI = AQI + sd.getAirQualityIndex();
                                    temp = temp + sd.getCarbonMonooxide();
                                    count++;
                                }
                            }
                        }
                        if (count > 0) {
                            Object[] row = new Object[4];
                            row[0] = o.getName();
                            row[1] = AQI / count;
                            row[2] = temp / count;
                            row[3] = getAQstatus(AQI / count);
                            model.addRow(row);
                        }
                    }
                }
            }
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

        MailPopupMenu = new javax.swing.JPopupMenu();
        menuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        environmentConditionsJTable = new javax.swing.JTable();
        btnGetData = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        backJButton1 = new javax.swing.JButton();

        MailPopupMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                MailPopupMenuMouseReleased(evt);
            }
        });

        menuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/EPAWork/mail icon.png"))); // NOI18N
        menuItem1.setText("Send Mail");
        menuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem1ActionPerformed(evt);
            }
        });
        MailPopupMenu.add(menuItem1);

        setBackground(new java.awt.Color(133, 210, 226));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        environmentConditionsJTable.setBorder(new javax.swing.border.MatteBorder(null));
        environmentConditionsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Community", "AQI", "CO", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        environmentConditionsJTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                environmentConditionsJTableMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                environmentConditionsJTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(environmentConditionsJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(58, 58, 670, 150));

        btnGetData.setBackground(new java.awt.Color(153, 153, 153));
        btnGetData.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        btnGetData.setText("Get Data");
        btnGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDataActionPerformed(evt);
            }
        });
        add(btnGetData, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 270, -1, -1));

        lblDay.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        lblDay.setText("Environment Conditions");
        add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, -1, -1));

        jDateChooser.setBackground(new java.awt.Color(153, 153, 153));
        add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 200, -1));

        backJButton1.setBackground(new java.awt.Color(153, 153, 153));
        backJButton1.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton1.setText("<<Back");
        backJButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton1ActionPerformed(evt);
            }
        });
        add(backJButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDataActionPerformed

        Date selectedDate = jDateChooser.getDate();

        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a Date!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        populateTable(selectedDate);

    }//GEN-LAST:event_btnGetDataActionPerformed

    private void backJButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton1ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButton1ActionPerformed

    private void environmentConditionsJTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_environmentConditionsJTableMouseClicked
        // TODO add your handling code here:
        if (evt.getButton() == java.awt.event.MouseEvent.BUTTON3) {
            //System.out.println("Right Click");
            MailPopupMenu.show(menuItem1, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_environmentConditionsJTableMouseClicked

    private void menuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem1ActionPerformed
        // TODO add your handling code here:

        int selectedRow = environmentConditionsJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        Organization o = (Organization) environmentConditionsJTable.getValueAt(selectedRow, 0);
        SendMailJPanel smjp = new SendMailJPanel(userProcessContainer, n, (CommunityOrganization) o);
        userProcessContainer.add("SendMailJPanel", smjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_menuItem1ActionPerformed

    private void MailPopupMenuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MailPopupMenuMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_MailPopupMenuMouseReleased

    private void environmentConditionsJTableMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_environmentConditionsJTableMouseReleased
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            //System.out.println("Hello 2");
            MailPopupMenu.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_environmentConditionsJTableMouseReleased

    public String getAQstatus(int avgAQI) {
        String status = "Good";
        if (avgAQI > 50 && avgAQI <= 100) {
            status = "Moderate";
        } else if (avgAQI > 100 && avgAQI <= 150) {
            status = "Unhealthy for Sensitive People";
        } else if (avgAQI > 150 && avgAQI <= 200) {
            status = "Unhealthy";
        } else if (avgAQI > 200 && avgAQI <= 300) {
            status = "Very Unhealthy";
        } else if (avgAQI > 300) {
            status = "Hazardous";
        }
        return status;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu MailPopupMenu;
    private javax.swing.JButton backJButton1;
    private javax.swing.JButton btnGetData;
    private javax.swing.JTable environmentConditionsJTable;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDay;
    private javax.swing.JMenuItem menuItem1;
    // End of variables declaration//GEN-END:variables
}
