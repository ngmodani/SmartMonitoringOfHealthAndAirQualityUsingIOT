/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Person;

import business.Community.House;

/**
 *
 * @author ngmodani
 */
public class Person {

    private String name;
    private int ID;
    // private Date DOB;

    private House house;
    private String eMailID;

    private static int count = 1;

    public Person() {
        ID = count;
        count++;
        //     house = new House();
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String geteMailID() {
        return eMailID;
    }

    public void seteMailID(String eMailID) {
        this.eMailID = eMailID;
    }

    @Override
    public String toString() {
        return String.valueOf(ID) + "-" + name;
    }

}
