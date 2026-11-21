package com.interview.assignment.sensors_emulator.sensors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

public class SensorRead implements Serializable{
    private Sensor sensor;
    private Timestamp timestamp; // TODO: usare Instant? eventualmente va gestita la conversione json
    private Double temperature;
    private Integer humidityPercentage;
    private Integer light;


    public SensorRead() {} 
    
    public SensorRead(Sensor sensor) {
        this.sensor = sensor;
        this.timestamp = new Timestamp(System.currentTimeMillis());
        this.temperature = ThreadLocalRandom.current().nextDouble(-20d, 45d);
        this.humidityPercentage = ThreadLocalRandom.current().nextInt(0,100);
        this.light = ThreadLocalRandom.current().nextInt(0,4000); // non ho idea di quali valori siano appropriati
    }

    public Sensor getSensor() {
        return sensor;
    }
    public void setSensorId(Sensor sensor) {
        this.sensor = sensor;
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
        SensorRead other = (SensorRead) obj;
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
