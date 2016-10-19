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

/**
 *
 * @author ngmodani
 */
public abstract class Role {
    
    
    public enum RoleType{
        Admin("AdminHH"),
        AdminHospital("AdminHospital"),
        Resident("Resident Role"),
        Doctor("Doctor Role"),
        HealthOfficial("Health Official"),
        Mayor("Mayor"),
        EnvironmentOfficial("EPA Official"),
        Accountant("Accountant")
        ;
        
        private String value;
        private RoleType(String value){
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }
    
    public abstract JPanel createWorkArea(JPanel userProcessContainer, UserAccount account, Organization organization, Enterprise enterprise, EcoSystem system,Network net);
    
    @Override
    public String toString() {
        return this.getClass().getName();
    }
    
    
}
