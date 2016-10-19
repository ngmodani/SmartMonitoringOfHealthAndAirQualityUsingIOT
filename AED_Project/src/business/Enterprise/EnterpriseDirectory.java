/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Enterprise;

import business.Organization.Organization;
import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class EnterpriseDirectory {
    
    private ArrayList<Enterprise> enterpriseList;

    public EnterpriseDirectory() {
        enterpriseList = new ArrayList<>();
    }

    public ArrayList<Enterprise> getEnterpriseList() {
        return enterpriseList;
    }
    
    public Enterprise createAndAddEnterprise(String name, Enterprise.EnterpriseType type){
        Enterprise enterprise = null;
        if (type == Enterprise.EnterpriseType.Hospital){
            enterprise = new HospitalEnterprise(name);
            enterpriseList.add(enterprise);
            //enterprise.getOrganizationDirectory().createOrganization(Organization.Type.Doctor);
        }
        if (type == Enterprise.EnterpriseType.CityCommunities){
            enterprise = new CityCommunities(name);
            enterpriseList.add(enterprise);
        }
        if (type == Enterprise.EnterpriseType.CityCouncil){
            enterprise = new CityCouncilEnterprise(name);
            enterpriseList.add(enterprise);
        }
        return enterprise;
    }
    
}
