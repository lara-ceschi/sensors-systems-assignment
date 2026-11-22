package com.interview.assignment.sensor_data_persistence.components;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.interview.assignment.sensor_data_persistence.entities.Sensor;
import com.interview.assignment.sensor_data_persistence.entities.SensorReading;
import com.interview.assignment.sensor_data_persistence.repository.SensorReadingRepository;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;

@Component 
public class SensorDataPersistenceProcessor {

    private final SensorReadingRepository sensorReadingRepository;
 

    public SensorDataPersistenceProcessor(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }



    @Transactional 
    public void convertAndInsert(SensorReadingDTO read) {
        SensorReading sensorReading = new SensorReading();
        
       
        sensorReading.setSensorId(read.getSensor() != null ? read.getSensor().getSensorId() : null);
       
        sensorReading.setLight(read.getLight());
        sensorReading.setHumidityPercentage(read.getHumidityPercentage());
        sensorReading.setTemperature(read.getTemperature());
        sensorReading.setTimestamp(read.getTimestamp());

        sensorReadingRepository.save(sensorReading); 
        // TODO: attenzione, è una upsert, verificare che sia il comportamento voluto
    }
}
