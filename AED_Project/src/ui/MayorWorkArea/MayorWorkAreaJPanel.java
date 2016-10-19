/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.MayorWorkArea;

import business.Enterprise.CityCommunities;
import business.Enterprise.CityCouncilEnterprise;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.CommunityOrganization;
import business.Organization.MayorOrganization;
import business.Organization.Organization;
import business.Sensor.Sensor;
import business.Sensor.SensorData;
import business.UserAccount.UserAccount;
import business.WorkQueue.WorkRequest;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author raunak
 */
public class MayorWorkAreaJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    //private Enterprise e;
    private Network n;
    private UserAccount userAccount;
    private DefaultCategoryDataset statsDataset;
    private Date currDate;
    private Date selectedDate;
    private DateFormat df;

    /**
     * Creates new form LabAssistantWorkAreaJPanel
     */
    public MayorWorkAreaJPanel(JPanel userProcessContainer, Network n, UserAccount ua) {
        initComponents();

        this.userProcessContainer = userProcessContainer;
        this.n = n;
        userAccount = ua;
        enterpriseLabel.setText("Welcome Mayor " + ua.getPerson().getName());
        df = new SimpleDateFormat("yyyy-MM-dd");
        currDate = new Date();
        populateStatsTable(currDate);
        populateUpdatesTable(currDate);
        enterpriseLabel.setText("Welcome Mayor " + ua.getPerson().getName());
        //functionsPopUpMenu.addPopupMenuListener(null);
        //addMouseListener(new MousePopupListener());
    }

    void populateUpdatesTable(Date date) {
        DefaultTableModel model = (DefaultTableModel) updatesJTable.getModel();
        //DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        model.setRowCount(0);
        for (Enterprise e : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (e instanceof CityCouncilEnterprise) {
                for (Organization o : e.getOrganizationDirectory().getOrganizationList()) {
                    if (o instanceof MayorOrganization) {
                        for (WorkRequest request : o.getWorkQueue().getWorkRequestList()) {
                            if (request != null) {
                                if (df.format(date).equals(df.format(request.getRequestDate()))) {
                                    Object[] row = new Object[2];
                                    row[0] = request;
                                    row[1] = request.getStatus();

                                    model.addRow(row);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    void populateStatsTable(Date date) {
        statsDataset = new DefaultCategoryDataset();
        DefaultTableModel model = (DefaultTableModel) statsJTable1.getModel();

        int docAssissts = 0;
        int age = 0;
        int population = 0;
        int AQI = 0;
        int count = 0;
        model.setRowCount(0);

        for (Enterprise ep : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (ep instanceof CityCommunities) {
                for (Organization o : ep.getOrganizationDirectory().getOrganizationList()) {
                    if (o instanceof CommunityOrganization) {

                        AQI = 0;            //initializing AQI to 0 for each Community
                        count = 0;          //counter to take count of Readings to find Avg.
                        for (Sensor s : o.getSensorList().getList()) {
                            for (SensorData sd : s.getSensorDataRecord()) {
                                if (df.format(date).equals(df.format(sd.getTimestamp()))) {
                                    AQI = AQI + sd.getAirQualityIndex();
                                    count++;
                                }
                            }
                        }
                        docAssissts = 0;           //initializing count to 0 for each Community
                        age = 0;
                        population = o.getUserAccountDirectory().getUserAccountList().size();

                        for (UserAccount ua : o.getUserAccountDirectory().getUserAccountList()) {
                            age = age + ua.getResident().getAge();
                            for (WorkRequest wr : ua.getWorkQueue().getWorkRequestList()) {
                                if (df.format(date).equals(df.format(wr.getRequestDate()))) {
                                    docAssissts++;
                                }
                            }
                        }

                        if (AQI > 0) {

                            Object[] row = new Object[5];
                            row[0] = o;
                            row[1] = docAssissts == 0 ? "No Health Issues" : docAssissts;
                            row[2] = getAQstatus(AQI / count);
                            row[3] = age / population;
                            row[4] = population;
                            model.addRow(row);
                            statsDataset.addValue(docAssissts, "No. of Medical Issues", o.getName());
                            statsDataset.addValue(AQI, "AQI", o.getName());

                        }
                    }
                }
            }
        }
    }

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

    private void createChart() {

        JFreeChart vitalSignChart = ChartFactory.createBarChart3D("Statistical Details for " + df.format(selectedDate),
                "Community", "Value", statsDataset, PlotOrientation.VERTICAL, true, false, false);
        vitalSignChart.setBackgroundPaint(Color.white);
        CategoryPlot vitalSignChartPlot = vitalSignChart.getCategoryPlot();
        vitalSignChartPlot.setBackgroundPaint(Color.lightGray);

        CategoryAxis vitalSignDomainAxis = vitalSignChartPlot.getDomainAxis();
        vitalSignDomainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );

        NumberAxis vitalSignRangeAxis = (NumberAxis) vitalSignChartPlot.getRangeAxis();
        vitalSignRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartFrame chartFrame = new ChartFrame("City Stats Chart", vitalSignChart);
        chartFrame.setVisible(true);
        chartFrame.setSize(800, 800);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        functionsPopUpMenu = new javax.swing.JPopupMenu();
        ChartItem = new javax.swing.JMenuItem();
        broadcastItem = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        updatesJTable = new javax.swing.JTable();
        btnGetData = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        statsJTable1 = new javax.swing.JTable();
        enterpriseLabel = new javax.swing.JLabel();

        functionsPopUpMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                functionsPopUpMenuMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                functionsPopUpMenuMouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                functionsPopUpMenuMouseClicked(evt);
            }
        });

        ChartItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/MayorWorkArea/Chart icon.png"))); // NOI18N
        ChartItem.setText("Statistical Chart");
        ChartItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChartItemActionPerformed(evt);
            }
        });
        functionsPopUpMenu.add(ChartItem);

        broadcastItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/MayorWorkArea/mail icon.png"))); // NOI18N
        broadcastItem.setText("Broadcast Mail");
        broadcastItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                broadcastItemActionPerformed(evt);
            }
        });
        functionsPopUpMenu.add(broadcastItem);

        setBackground(new java.awt.Color(164, 119, 175));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                formMouseReleased(evt);
            }
        });
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        updatesJTable.setBorder(new javax.swing.border.MatteBorder(null));
        updatesJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Message", "Department"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(updatesJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 630, 96));

        btnGetData.setBackground(new java.awt.Color(153, 153, 153));
        btnGetData.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        btnGetData.setText("Get Data");
        btnGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetDataActionPerformed(evt);
            }
        });
        add(btnGetData, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 360, -1, -1));

        lblDay.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        lblDay.setText("Air & Medical Records Statistics");
        add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 340, -1));

        jDateChooser.setBackground(new java.awt.Color(153, 153, 153));
        add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 360, 200, -1));

        statsJTable1.setBorder(new javax.swing.border.MatteBorder(null));
        statsJTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Community", "Health Issues", "AQI Status", "Avg Age", "Population"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        statsJTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                statsJTable1MouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(statsJTable1);

        add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 630, 96));

        enterpriseLabel.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        enterpriseLabel.setText("Welcome ");
        add(enterpriseLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 440, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetDataActionPerformed

        selectedDate = jDateChooser.getDate();

        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a Date.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        populateStatsTable(selectedDate);
        populateUpdatesTable(selectedDate);
    }//GEN-LAST:event_btnGetDataActionPerformed

    private void formMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseReleased
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            //System.out.println("Hello 2");
            functionsPopUpMenu.show(MayorWorkAreaJPanel.this, evt.getX(), evt.getY());
        }

    }//GEN-LAST:event_formMouseReleased

    private void ChartItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChartItemActionPerformed
        // TODO add your handling code here:
        createChart();
    }//GEN-LAST:event_ChartItemActionPerformed

    private void broadcastItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_broadcastItemActionPerformed
        // TODO add your handling code here:
        BroadcastMessageJPanel bmjp = new BroadcastMessageJPanel(userProcessContainer, n);
        userProcessContainer.add("BroadcastMessageJPanel", bmjp);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.next(userProcessContainer);
    }//GEN-LAST:event_broadcastItemActionPerformed

    private void functionsPopUpMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_functionsPopUpMenuMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_functionsPopUpMenuMouseClicked

    private void functionsPopUpMenuMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_functionsPopUpMenuMousePressed
        // TODO add your handling code here:

    }//GEN-LAST:event_functionsPopUpMenuMousePressed

    private void functionsPopUpMenuMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_functionsPopUpMenuMouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_functionsPopUpMenuMouseReleased

    private void statsJTable1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statsJTable1MouseReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_statsJTable1MouseReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ChartItem;
    private javax.swing.JMenuItem broadcastItem;
    private javax.swing.JButton btnGetData;
    private javax.swing.JLabel enterpriseLabel;
    private javax.swing.JPopupMenu functionsPopUpMenu;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblDay;
    private javax.swing.JTable statsJTable1;
    private javax.swing.JTable updatesJTable;
    // End of variables declaration//GEN-END:variables
}
