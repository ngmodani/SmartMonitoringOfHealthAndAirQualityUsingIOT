/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import business.Organization.Organization.Type;
import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class OrganizationDirectory {

    private ArrayList<Organization> organizationList;

    public OrganizationDirectory() {
        organizationList = new ArrayList<>();
    }

    public ArrayList<Organization> getOrganizationList() {
        return organizationList;
    }

    //for creating all other organizations
    public Organization createOrganization(Type type){
        Organization organization = null;
        if (type.getValue().equals(Type.Doctor.getValue())){
            organization = new DoctorOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.AdminHH.getValue())){
            organization = new AdminHHOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.AdminCC.getValue())){
            organization = new AdminCCOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.AdminHospital.getValue())){
            organization = new AdminHospitalOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.HealthDepartment.getValue())){
            organization = new HealthDeptOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.EnvironmentDepartment.getValue())){
            organization = new EnvironmentDeptOrganization();
            organizationList.add(organization);
        }
        else if (type.getValue().equals(Type.Mayor.getValue())){
            organization = new MayorOrganization();
            organizationList.add(organization);
        }
        return organization;
    }

//for creating community organization    
    public Organization createOrganization(String name,int zip) {
        Organization organization = null;

            organization = new CommunityOrganization(name,zip);
            organizationList.add(organization);

        return organization;
    }
}
