package com.interview.assignment.sensor_datatypes;

import java.io.Serializable;

/**
 * Classe che identifica un sensore, identificato dall'id del sensore stesso e dall'id 
 * dell'impianto in cui il sensore si trova
 * Sensor
 */
public class SensorDTO implements Serializable{
    
    private Long sensorId;
    private Long plantId;

   public SensorDTO() {
    }
   public SensorDTO(Long sensorId, Long plantId) {
        this.sensorId = sensorId;
        this.plantId = plantId;
    }
    public Long getSensorId() {
        return sensorId;
    }
    public void setSensorId(Long sensorId) {
        this.sensorId = sensorId;
    }
    public Long getPlantId() {
        return plantId;
    }
    public void setPlantId(Long plantId) {
        this.plantId = plantId;
    }
    @Override
    public String toString() {
        return "Sensor [sensorId=" + sensorId + ", plantId=" + plantId + "]";
    }
   

}
