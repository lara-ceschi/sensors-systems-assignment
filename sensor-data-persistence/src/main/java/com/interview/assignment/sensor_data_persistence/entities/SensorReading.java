package com.interview.assignment.sensor_data_persistence.entities;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity 
public class SensorReading {

    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long sensorId;
    private Timestamp timestamp;
    private Double temperature;
    private Integer humidityPercentage;
    private Integer light;
    
    public SensorReading() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidityPercentage() {
        return humidityPercentage;
    }

    public void setHumidityPercentage(Integer humidityPercentage) {
        this.humidityPercentage = humidityPercentage;
    }

    public Integer getLight() {
        return light;
    }

    public void setLight(Integer light) {
        this.light = light;
    }

    public Long getSensorId() {
        return sensorId;
    }

    public void setSensorId(Long sensorId) {
        this.sensorId= sensorId;
    }


    
}
