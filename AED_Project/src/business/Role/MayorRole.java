/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Role;

import business.Ecosystem.EcoSystem;
import business.Enterprise.CityCouncilEnterprise;
import business.Enterprise.Enterprise;
import business.Network.Network;
import business.Organization.Organization;
import business.UserAccount.UserAccount;
import javax.swing.JPanel;
import ui.MayorWorkArea.MayorWorkAreaJPanel;

/**
 *
 * @author ngmodani
 */
public class MayorRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, UserAccount ua, Organization organization, Enterprise enterprise, EcoSystem system,Network n) {
        return new MayorWorkAreaJPanel(userProcessContainer,n,ua);
    }

    @Override
    public String toString() {
        return "Mayor Role";
    }
    
}
