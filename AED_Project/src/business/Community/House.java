/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Community;

import business.Sensor.Sensor;

/**
 *
 * @author ngmodani
 */
public class House {
    
    private int houseID;
    private static int count=0;
    private Sensor sensor;
    private String address;
    private String apartmentNo;
    private int zipCode;    


    public House() {
        count++;
        houseID=count;
        sensor = new Sensor();
    }

    public int getHouseID() {
        return houseID;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public String getApartmentNo() {
        return apartmentNo;
    }

    public void setApartmentNo(String apartmentNo) {
        this.apartmentNo = apartmentNo;
    }

    
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return address+" Apt. "+apartmentNo; 
    }
    
    
    
    
}
