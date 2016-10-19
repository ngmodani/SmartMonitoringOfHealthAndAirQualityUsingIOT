/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;

import business.Ecosystem.EcoSystem;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import javax.swing.JPanel;
import ui.HealthDeptWork.AvgVitalsJPanel;
import ui.HealthDeptWork.HOWorkAreaJPanel;

/**
 *
 * @author ngmodani
 */
public class HealthOfficerRole extends Role {

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount ua, Organization organization, Enterprise enterprise, EcoSystem system,Network n) {
        return new HOWorkAreaJPanel(userProcessContainer, n, ua);
    }
    
    @Override
    public String toString() {
        return "Health Officer Role"; //To change body of generated methods, choose Tools | Templates.
    }

}
