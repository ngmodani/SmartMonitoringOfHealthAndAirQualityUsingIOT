/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Sensor;

import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class Sensor {
    
    private int sensorID;
    private static int count=0;
    private ArrayList<SensorData> sensorDataRecord;
    
    public Sensor() {
        count++;
        sensorID=count;
        sensorDataRecord = new ArrayList<>();
    }

    public ArrayList<SensorData> getSensorDataRecord() {
        return sensorDataRecord;
    }
    
    public SensorData addSendorData(){
        SensorData sd = new SensorData();
        sensorDataRecord.add(sd);
        return sd;
    }
    
    public void deleteSensorData(SensorData sd){
        sensorDataRecord.remove(sd);
    }
    
}
