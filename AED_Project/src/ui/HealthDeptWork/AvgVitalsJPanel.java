/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.HealthDeptWork;

import business.Community.VitalSign;
import business.Enterprise.CityCommunities;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.CommunityOrganization;
import business.Organization.DoctorOrganization;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import java.awt.CardLayout;
import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.jfree.data.general.DefaultPieDataset;

/**
 *
 * @author raunak
 */
public class AvgVitalsJPanel extends javax.swing.JPanel {

    private JPanel userProcessContainer;
    private Enterprise e;
    private Network n;
    private UserAccount userAccount;
    private DoctorOrganization docOrganization;
    private Date currDate;
    private DefaultCategoryDataset vitalSignDataset;
    

    /**
     * Creates new form LabAssistantWorkAreaJPanel
     */
    public AvgVitalsJPanel(JPanel userProcessContainer, Network n) {
        initComponents();

        this.userProcessContainer = userProcessContainer;

        //this.e = e;
        this.n = n;
        currDate = new Date();
        populateTable(currDate);

    }

    public void populateTable(Date date) {
        DefaultTableModel model = (DefaultTableModel) environmentConditionsJTable.getModel();
        model.setRowCount(0);
        vitalSignDataset = new DefaultCategoryDataset();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        int HR = 0;
        int BP = 0;
        int weight = 0;
        int RR = 0;
        int docAssissts = 0;
        int age = 0;
        int population = 0;
        int count = 0;
        model.setRowCount(0);

        for (Enterprise ep : n.getEnterpriseDirectory().getEnterpriseList()) {
            if (ep instanceof CityCommunities) {
                for (Organization o : ep.getOrganizationDirectory().getOrganizationList()) {
                    if (o instanceof CommunityOrganization) {

                        population = o.getPopulation();
                        HR = 0;           //initializing HR to 0 for each Community
                        BP = 0;           //initializing BP to 0 for each Community
                        RR = 0;           //initializing RR to 0 for each Community
                        weight = 0;           //initializing weight to 0 for each Community
                        age = 0;           //initializing age to 0 for each Community

                        count = 0;          //counter to take count of Readings to find Avg.
                        docAssissts = 0;      //counter to take count of Number of Times Doc Advice asked
                        for (UserAccount ua : o.getUserAccountDirectory().getUserAccountList()) {
                            if (ua.getResident() != null) {
                                //population++;
                                age = age + ua.getResident().getAge();
                                docAssissts = docAssissts + ua.getWorkQueue().getWorkRequestList().size();
                                for (VitalSign vs : ua.getResident().getVsHistory().getHistory()) {
                                    if (df.format(date).equals(df.format(vs.getTimestamp()))) {
                                        HR = HR + vs.getHeartRate();
                                        RR = RR + vs.getRespiratoryRate();
                                        BP = BP + vs.getBloodPressure();
                                        weight = weight + vs.getWeight();
                                        count++;
                                    }
                                }
                            }
                        }
                        if (count > 0) {
                            Object[] row = new Object[7];
                            row[0] = o.getName();
                            row[1] = HR / count;
                            
                            row[2] = RR / count;
                            row[3] = BP / count;
                            row[4] = weight / count;
                            //row[5] = docAssissts;
                            row[5] = age / population;
                            row[6] = vitalSignStatus(age / population, HR / count, RR / count, BP / count, weight / count);
                            model.addRow(row);
                            vitalSignDataset.addValue(RR/count, "RR", o.getName());
                            vitalSignDataset.addValue(HR/count, "HR", o.getName());
                            vitalSignDataset.addValue(BP/count, "BP", o.getName());
                            vitalSignDataset.addValue(weight/count, "Weight", o.getName());
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

        jScrollPane1 = new javax.swing.JScrollPane();
        environmentConditionsJTable = new javax.swing.JTable();
        btnShowChart = new javax.swing.JButton();
        lblDay = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        backJButton2 = new javax.swing.JButton();
        btnGetData1 = new javax.swing.JButton();

        setBackground(new java.awt.Color(52, 169, 66));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        environmentConditionsJTable.setBorder(new javax.swing.border.MatteBorder(null));
        environmentConditionsJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Community", "Avg HR", "Avg RR", "Avg BP", "Avg Weight", "Avg Age", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(environmentConditionsJTable);

        add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(38, 58, 630, 96));

        btnShowChart.setBackground(new java.awt.Color(153, 153, 153));
        btnShowChart.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        btnShowChart.setText("Show Chart");
        btnShowChart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowChartActionPerformed(evt);
            }
        });
        add(btnShowChart, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 280, -1, -1));

        lblDay.setFont(new java.awt.Font("New Peninim MT", 1, 18)); // NOI18N
        lblDay.setText("Average Health Statistics (Day-wise)");
        add(lblDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, -1, -1));

        jDateChooser.setBackground(new java.awt.Color(153, 153, 153));
        add(jDateChooser, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 210, 200, -1));

        backJButton2.setBackground(new java.awt.Color(153, 153, 153));
        backJButton2.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        backJButton2.setText("<<Back");
        backJButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backJButton2ActionPerformed(evt);
            }
        });
        add(backJButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, -1, -1));

        btnGetData1.setBackground(new java.awt.Color(153, 153, 153));
        btnGetData1.setFont(new java.awt.Font("Chalkduster", 1, 14)); // NOI18N
        btnGetData1.setText("Get Data");
        btnGetData1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGetData1ActionPerformed(evt);
            }
        });
        add(btnGetData1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 210, -1, -1));
    }// </editor-fold>//GEN-END:initComponents

    private void btnShowChartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowChartActionPerformed

        Date selectedDate = jDateChooser.getDate();

        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a Date.", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if(vitalSignDataset.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "No data available to show chart!", "Error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        generateAgeDistributionPieChartOfCommunity(selectedDate);

    }//GEN-LAST:event_btnShowChartActionPerformed

    private void backJButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backJButton2ActionPerformed
        // TODO add your handling code here:
        userProcessContainer.remove(this);
        CardLayout layout = (CardLayout) userProcessContainer.getLayout();
        layout.previous(userProcessContainer);
    }//GEN-LAST:event_backJButton2ActionPerformed

    private void btnGetData1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGetData1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGetData1ActionPerformed

    public void generateAgeDistributionPieChartOfCommunity(Date date) {
        
        JFreeChart vitalSignChart = ChartFactory.createBarChart3D("Vital Sign Chart", "Community", "Value", vitalSignDataset, PlotOrientation.VERTICAL, true, false, false);
        vitalSignChart.setBackgroundPaint(Color.white);
        CategoryPlot vitalSignChartPlot = vitalSignChart.getCategoryPlot();
        vitalSignChartPlot.setBackgroundPaint(Color.lightGray);

        CategoryAxis vitalSignDomainAxis = vitalSignChartPlot.getDomainAxis();
        vitalSignDomainAxis.setCategoryLabelPositions(
                CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );

        NumberAxis vitalSignRangeAxis = (NumberAxis) vitalSignChartPlot.getRangeAxis();
        vitalSignRangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartFrame chartFrame = new ChartFrame("Chart", vitalSignChart);
        chartFrame.setVisible(true);
        chartFrame.setSize(800, 800);
    }

    private String vitalSignStatus(int patientAge, int HR, int RR, int BP, float w) {
        String vitalSignStatus = "Normal";

        /*Toddler   age 1-3 */
        if (patientAge >= 1 && patientAge <= 3) {
            if ((RR < 20 || RR > 30) /*Respiration Rate*/
                    || (HR < 80 || HR > 130) /*Heart Rate*/
                    || (BP < 80 || BP > 110) /*Blood Pressure*/
                    || (w < 22 || w > 31)) /*Weight*/ {
                vitalSignStatus = "Abnormal";
            }
        }
        /*Preschooler   age 4-5 */
        if (patientAge >= 4 && patientAge <= 5) {
            if ((RR < 20 || RR > 30)
                    || (HR < 80 || HR > 120)
                    || (BP < 80 || BP > 110)
                    || (w < 31 || w > 40)) {
                vitalSignStatus = "Abnormal";
            }
        }
        /*School Age    age 6-12 */
        if (patientAge >= 6 && patientAge <= 12) {
            if ((RR < 20 || RR > 30)
                    || (HR < 70 || HR > 110)
                    || (BP < 80 || BP > 120)
                    || (w < 41 || w > 92)) {
                vitalSignStatus = "Abnormal";
            }
        }
        /*Adolescent    age 13 plus*/
        if (patientAge >= 13) {
            if ((RR < 12 || RR > 20)
                    || (HR < 55 || HR > 105)
                    || (BP < 110 || BP > 120)
                    || (w < 110)) {
                vitalSignStatus = "Abnormal";
            }
        }
        return vitalSignStatus;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backJButton2;
    private javax.swing.JButton btnGetData1;
    private javax.swing.JButton btnShowChart;
    private javax.swing.JTable environmentConditionsJTable;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDay;
    // End of variables declaration//GEN-END:variables
}
