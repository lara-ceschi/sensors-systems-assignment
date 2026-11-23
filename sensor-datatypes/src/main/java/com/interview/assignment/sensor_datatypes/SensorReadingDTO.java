package com.interview.assignment.sensor_datatypes;

import java.io.Serializable;
import java.time.Instant;

/**
 * Classe che definisce la lettura ottenuta da un sensore. <br/>
 * Definita dal sensore stesso, dal timestamp di ottenimento (che non deve essere futuro),
 * dalla rilevazione della temperatura (tra -20° e +45°), della percentuale di umidità (tra 0 e 100),
 * della luminosità (tra 0 e 4000).
 * 
 * SensorReading
 */
public class SensorReadingDTO implements Serializable{
    private SensorDTO sensor;
    private Instant timestamp; 
    private Double temperature;
    private Integer humidityPercentage;
    private Integer light;


    public SensorReadingDTO() {
    }
    public SensorDTO getSensor() {
        return sensor;
    }
    public void setSensorId(SensorDTO sensor) {
        this.sensor = sensor;
    }
    public Instant getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(Instant timestamp) {
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((sensor == null) ? 0 : sensor.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SensorReadingDTO other = (SensorReadingDTO) obj;
        if (sensor == null) {
            if (other.sensor != null)
                return false;
        } else if (!sensor.equals(other.sensor))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "SensorRead [sensor=" + sensor + ", timestamp=" + timestamp + ", temperature=" + temperature
                + ", humidityPercentage=" + humidityPercentage + ", light=" + light + "]";
    }


   
}
