package com.interview.assignment.sensor_data_persistence.components;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.interview.assignment.sensor_data_persistence.dao.SensorReadingServiceDAO;
import com.interview.assignment.sensor_data_persistence.entities.Sensor;
import com.interview.assignment.sensor_data_persistence.entities.SensorReading;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;

@Component 
public class SensorDataPersistenceProcessor {

    private final SensorReadingServiceDAO sensorReadingServiceDAO;

    

    public SensorDataPersistenceProcessor(SensorReadingServiceDAO sensorReadingServiceDAO) {
        this.sensorReadingServiceDAO = sensorReadingServiceDAO;
    }



    @Transactional 
    public void convertAndInsert(SensorReadingDTO read) {
        SensorReading sensorReading = new SensorReading();
        
        Sensor sensor = new Sensor();
        sensor.setSensorId(read.getSensor() != null ? read.getSensor().getSensorId() : null);
        sensor.setPlantId(read.getSensor() != null ? read.getSensor().getPlantId() : null);

        sensorReading.setSensor(sensor);
        sensorReading.setLight(read.getLight());
        sensorReading.setHumidityPercentage(read.getHumidityPercentage());
        sensorReading.setTemperature(read.getTemperature());
        sensorReading.setTimestamp(read.getTimestamp());

        sensorReadingServiceDAO.save(sensorReading); 
        // TODO: attenzione, è una upsert, verificare che sia il comportamento voluto
    }
}
