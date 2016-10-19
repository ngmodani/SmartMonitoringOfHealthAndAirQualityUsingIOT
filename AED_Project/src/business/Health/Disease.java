/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Health;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ngmodani
 */
public class Disease {
    
    private String diseaseDescription;
    private Date timeOfDisease;

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public Date getTimeOfDisease() {
        return timeOfDisease;
    }

    public void setTimeOfDisease(Date timeOfDisease) {
        this.timeOfDisease = timeOfDisease;
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm:ss a");
        return ft.format(timeOfDisease);
    }
    
    
}
