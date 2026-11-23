package com.interview.assignment.sensors_emulator.sensor;

public class SensorMock {
    private long sensorId;
    private long plantId;
    private Integer interval;
    private STATUS status;

    
    public enum STATUS {
        STARTED,
        STOPPED
    }


    
      public SensorMock(long sensorId, long plantId, STATUS status, Integer interval) {
        this.sensorId = sensorId;
        this.plantId = plantId;
        this.status = status;
        this.interval = interval;
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


    public STATUS getStatus() {
        return status;
    }


    public void setStatus(STATUS status) {
        this.status = status;
    }


    public Integer getInterval() {
        return interval;
    }


    public void setInterval(Integer interval) {
        this.interval = interval;
    }



}
