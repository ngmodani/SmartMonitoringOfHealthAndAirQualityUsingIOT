/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Community;

import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class HouseDirectory {

    private ArrayList<House> list;

    public HouseDirectory() {
        list = new ArrayList<>();
    }

    public ArrayList<House> getList() {
        return list;
    }

    public void setList(ArrayList<House> list) {
        this.list = list;
    }

    public House AddHouse() {
        House house = new House();
        list.add(house);
        return house;
    }

    public void deleteHouse(House house) {
        list.remove(house);
    }
}
