/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Community;

import business.Health.HealthRecord;

import business.Person.Person;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author ngmodani
 */
public class Resident extends Person {

    
    private Date DOB;
    private VitalSignHistory vsHistory;
    private HealthRecord healthRecord;

    public Resident() {
        vsHistory = new VitalSignHistory();
        healthRecord = new HealthRecord();
    }

    /*   public void createHouse(){
     House h = new House();
     h= house;
     }
     */

    public Date getDOB() {
        return DOB;
    }

    public void setDOB(Date DOB) {
        this.DOB = DOB;
    }

    public VitalSignHistory getVsHistory() {
        return vsHistory;
    }

    public void setVsHistory(VitalSignHistory vsHistory) {
        this.vsHistory = vsHistory;
    }

    public HealthRecord getHealthRecord() {
        return healthRecord;
    }

    public void setHealthRecord(HealthRecord healthRecord) {
        this.healthRecord = healthRecord;
    }

    //method to calculate Current age of a Person using his DOB
    public int getAge() {
        int age;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Calendar beginCalendar = Calendar.getInstance();
        beginCalendar.setTime(DOB);

        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(new Date());

        int minusDays = 0;       // for calculating no. of days
        while (true) {
            minusDays++;

            // Day increasing by 1
            beginCalendar.add(Calendar.DAY_OF_MONTH, 1);

            if (dateFormat.format(beginCalendar.getTime()).
                    equals(dateFormat.format(endCalendar.getTime()))) {
                break;
            }
        }
        //add code for calculation of age
        age = (minusDays + 1) / 365;

        return age;
    }

    @Override
    public String toString() {
        return String.valueOf(super.getID());
    }

}
