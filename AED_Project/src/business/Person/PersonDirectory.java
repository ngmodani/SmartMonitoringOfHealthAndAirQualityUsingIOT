/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Person;

import business.Community.Resident;
import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class PersonDirectory {
    
    private ArrayList<Person> personList;

    public PersonDirectory() {
        personList = new ArrayList<>();
    }

    public ArrayList<Person> getPersonList() {
        return personList;
    }
    //for adding normal person
    public Person createPerson(){
        Person person = new Person();
        personList.add(person);
        return person;
    }
    //for adding residents
    public Resident createResident(){
        Resident resident = new Resident();
        personList.add(resident);
        return resident;
    }
    
}
