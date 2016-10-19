/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import business.Role.AdminCCRole;
import business.Role.Role;
import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class AdminCCOrganization extends Organization{

    public AdminCCOrganization() {
        super(Type.AdminCC.getValue());
    }
    
    @Override
    public ArrayList<Role> getSupportedRole() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(new AdminCCRole());
        return roles;
    }


    
}
