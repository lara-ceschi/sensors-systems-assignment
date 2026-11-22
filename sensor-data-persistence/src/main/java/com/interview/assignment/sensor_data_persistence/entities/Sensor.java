package com.interview.assignment.sensor_data_persistence.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity 
public class Sensor {
    
    @Id 
    private Long sensorId;

    private Long plantId;


    public Sensor() {
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
    
}
