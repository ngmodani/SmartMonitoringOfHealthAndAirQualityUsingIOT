/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;


import business.Ecosystem.EcoSystem;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import javax.swing.JPanel;
import ui.SystemAdminWorkArea.SystemAdminWorkAreaJPanel;

/**
 *
 * @author raunak
 */
public class SystemAdminRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem system,Network n) {
        return new SystemAdminWorkAreaJPanel(userProcessContainer, system);
    }
    
}
