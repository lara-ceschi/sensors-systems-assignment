package com.interview.assignment.sensor_data_persistence.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity 
public class Sensor {
    
    @Id 
    private Long sensorId;

    private Long plantId;

    @OneToMany 
    private List<SensorReading> sensorReadings;

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
