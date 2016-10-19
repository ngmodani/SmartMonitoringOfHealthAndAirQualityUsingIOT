/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Sensor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ngmodani
 */
public class SensorData {

    private int airQualityIndex;
    private int carbonMonooxide;
    private Date timestamp;

    public int getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(int airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public int getCarbonMonooxide() {
        return carbonMonooxide;
    }

    public void setCarbonMonooxide(int carbonMonooxide) {
        this.carbonMonooxide = carbonMonooxide;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy 'at' hh:mm:ss a");
        return ft.format(timestamp);
    }

}
