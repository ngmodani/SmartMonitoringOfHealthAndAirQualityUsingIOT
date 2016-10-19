/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Health;

import java.util.ArrayList;

/**
 *
 * @author ngmodani
 */
public class HealthRecord {

    private ArrayList<Disease> history;

    public HealthRecord() {
        history = new ArrayList<>();
    }

    public ArrayList<Disease> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<Disease> history) {
        this.history = history;
    }

    public Disease createAndAddDisease() {
        Disease disease = new Disease();
        history.add(disease);
        return disease;
    }

    public void deleteDisease(Disease disease) {
        history.remove(disease);
    }
}
