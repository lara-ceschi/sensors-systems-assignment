package com.interview.assignment.sensor_data_persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table ("SENSOR") 
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
