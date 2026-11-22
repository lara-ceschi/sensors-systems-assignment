package com.interview.assignment.sensor_datatypes;

import java.io.Serializable;

/**
 * Classe che identifica un sensore, identificato dall'id del sensore stesso e dall'id 
 * dell'impianto in cui il sensore si trova
 * Sensor
 */
public class Sensor implements Serializable{
    private long sensorId;
    private long plantId;

   public Sensor() {
    }
   public Sensor(long sensorId, long plantId) {
        this.sensorId = sensorId;
        this.plantId = plantId;
    }
    public long getSensorId() {
        return sensorId;
    }
    public void setSensorId(long sensorId) {
        this.sensorId = sensorId;
    }
    public long getPlantId() {
        return plantId;
    }
    public void setPlantId(long plantId) {
        this.plantId = plantId;
    }
    @Override
    public String toString() {
        return "Sensor [sensorId=" + sensorId + ", plantId=" + plantId + "]";
    }
   

}
