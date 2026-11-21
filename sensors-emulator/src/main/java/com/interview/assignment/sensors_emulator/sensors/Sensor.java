package com.interview.assignment.sensors_emulator.sensors;

import java.io.Serializable;

public class Sensor implements Serializable{
    private long sensorId;
    private long plantId;

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
