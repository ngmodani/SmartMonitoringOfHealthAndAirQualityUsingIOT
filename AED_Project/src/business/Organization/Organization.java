/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Organization;

import business.Community.HouseDirectory;
import business.Person.PersonDirectory;
import business.Role.ResidentRole;
import business.Role.Role;
import business.Sensor.SensorList;
import business.UserAccount.UserAccountDirectory;
import business.WorkQueue.WorkQueue;
import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public abstract class Organization {

    private String name;
    private static int counter=0;
    private WorkQueue workQueue;
    private PersonDirectory personDirectory;
    private UserAccountDirectory userAccountDirectory;
    private int organizationID;
    //following 3 variables ll be reqd. for community org only
    private HouseDirectory houseList;
    private SensorList sensorList;
    ArrayList<Role> roles;
    private int population;

    
    public enum Type{
        AdminHH("AdminHH Organization"),
        AdminCC("AdminCC Organization"),
        Doctor("Doctor Organization"), 
        AdminHospital("AdminHospital Organization"),
        Resident("Resident Organization"),
        HealthDepartment("Health Department"),
        Mayor("Mayor"),
        EnvironmentDepartment("Environment Department");
        
        private String value;
        private Type(String value) {
            this.value = value;
        }
        public String getValue() {
            return value;
        }
    }

    public Organization(String name) {
        this.name = name;
        workQueue = new WorkQueue();
        personDirectory = new PersonDirectory();
        userAccountDirectory = new UserAccountDirectory();
        organizationID = counter;
        ++counter;
    }
    
    public Organization(String name,int ID){
        this.name = name;
        roles = new ArrayList<>();
        roles.add(new ResidentRole());
        houseList = new HouseDirectory();
        sensorList = new SensorList();
        workQueue = new WorkQueue();
        personDirectory = new PersonDirectory();
        userAccountDirectory = new UserAccountDirectory();
        organizationID = ID;
    }

    public abstract ArrayList<Role> getSupportedRole();
    
    public UserAccountDirectory getUserAccountDirectory() {
        return userAccountDirectory;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getPopulation() {
        return population;
    }

    
    public int getOrganizationID() {
        return organizationID;
    }
    
    public String getName() {
        return name;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public PersonDirectory getPersonDirectory() {
        return personDirectory;
    }

    public HouseDirectory getHouseList() {
        return houseList;
    }


    public SensorList getSensorList() {
        return sensorList;
    }

    
    @Override
    public String toString() {
        return String.valueOf(organizationID)+" - "+name;
    }
    
}
