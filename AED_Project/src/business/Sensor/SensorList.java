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
public class SensorList {

    private ArrayList<Sensor> list;

    public SensorList() {
        list = new ArrayList<>();
    }

    public ArrayList<Sensor> getList() {
        return list;
    }

    public void setList(ArrayList<Sensor> list) {
        this.list = list;
    }

    public void createAndAddSensor(int number) {
        for (int i = 0; i < number; i++) {
            Sensor sensor = new Sensor();
            list.add(sensor);
        }
    }

}
