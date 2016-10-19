/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.EPAWork;

import business.Organization.EnvironmentDeptOrganization;
import business.UserAccount.UserAccount;
import business.WorkQueue.DocAdviceWorkRequest;
import business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author raunak
 */
public class ViewRequestsJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    //private Enterprise e;
    private UserAccount userAccount;
    private EnvironmentDeptOrganization epaOrganization;
    //private Network n;

    /**
     * Creates new form LabAssistantWorkAreaJPanel
     */
    public ViewRequestsJPanel(JPanel userProcessContainer, EnvironmentDeptOrganization o, UserAccount account) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.userAccount = account;
        this.epaOrganization = o;

        // lblDay.setText(account.getPerson().getName());
        populateTable();
    }

    public void populateTable() {
        DefaultTableModel model = (DefaultTableModel) workRequestJTable.getModel();

        model.setRowCount(0);

        for (WorkRequest request : epaOrganization.getWorkQueue().getWorkRequestList()) {
            if (request != null) {
                Object[] row = new Object[6];
                row[0] = request;
                row[1] = request.getSender().getPerson().getName();
                row[2] = request.getReceiver() == null ? null : request.getReceiver().getPerson().getName();
                row[3] = request.getStatus();
                row[4] = request.getRequestDate();
                row[5] = request.getResolveDate();
                model.addRow(row);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        workRequestJTable = new javax.swing.JTable();
        assignJButton = new javax.swing.JButton();
        processJButton = new javax.swing.JButton();
        refreshJButton = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();
        backJButton2 = new javax.swing.JButton();

        setBackground(new java.awt.Color(133, 210, 226));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        workRequestJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Sender", "Receiver", "Status", "Request Date", "Resolve Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(workRequestJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 58, 610, 96));

        assignJButton.setBackground(new java.awt.Color(153, 153, 153));
        assignJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        assignJButton.setText("Assign to me");
        assignJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                assignJButtonActionPerformed(evt);
            }
        });
        add(assignJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        processJButton.setBackground(new java.awt.Color(153, 153, 153));
        processJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        processJButton.setText("Process");
        processJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                processJButtonActionPerformed(evt);
            }
        });
        add(processJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 215, -1, -1));

        refreshJButton.setBackground(new java.awt.Color(153, 153, 153));
        refreshJButton.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        refreshJButton.setText("Refresh");
        refreshJButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshJButtonActionPerformed(evt);
            }
        });
        add(refreshJButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(406, 26, -1, -1));

        lblDay.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        lblDay.setText("Requests From Health Dept.");
        add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        backJButton2.setBackground(new java.awt.Color(153, 153, 153));
        backJButton2.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton2.setText("<<Back");
        backJButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton2ActionPerformed(evt);
            }
        });
        add(backJButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void assignJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_assignJButtonActionPerformed

        int selectedRow = workRequestJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table!.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        WorkRequest request = (WorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
        if (request.getReceiver() != null) {
            JOptionPane.showMessageDialog(this, "Request already Assigned!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        request.setReceiver(userAccount);
        request.setStatus("Pending");
        populateTable();

    }//GEN-LAST:event_assignJButtonActionPerformed

    private void processJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_processJButtonActionPerformed

        int selectedRow = workRequestJTable.getSelectedRow();

        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row from table!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        DocAdviceWorkRequest request = (DocAdviceWorkRequest) workRequestJTable.getValueAt(selectedRow, 0);
        if (request.getReceiver() == null) {
            JOptionPane.showMessageDialog(this, "Please assign the request!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (request.getReceiver() != userAccount) {
            JOptionPane.showMessageDialog(this, "Request do not Belong to you!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (request.getResolveDate() != null) {
            JOptionPane.showMessageDialog(this, "Request already Processed!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        request.setStatus("Processing");

        ProcessWorkRequestJPanel pwrjp = new ProcessWorkRequestJPanel(userProcessContainer, request);
        userProcessContainer.add("ProcessWorkRequestJPanel", pwrjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);

    }//GEN-LAST:event_processJButtonActionPerformed

    private void refreshJButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshJButtonActionPerformed
        populateTable();
    }//GEN-LAST:event_refreshJButtonActionPerformed

    private void backJButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton2ActionPerformed
        // TODO add your handling code here:
        //vrjp.pop
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton assignJButton;
    private javax.swing.JButton backJButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDay;
    private javax.swing.JButton processJButton;
    private javax.swing.JButton refreshJButton;
    private javax.swing.JTable workRequestJTable;
    // End of variables declaration//GEN-END:variables
}