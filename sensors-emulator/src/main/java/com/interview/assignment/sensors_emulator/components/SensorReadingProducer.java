package com.interview.assignment.sensors_emulator.components;


import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorDTO;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;
import com.interview.assignment.sensors_emulator.sensor.SensorMock;
import com.interview.assignment.sensors_emulator.sensor.SensorsHandler;



@Component  
public class SensorReadingProducer {
private static final Logger log = LoggerFactory.getLogger(SensorReadingProducer.class);

    private final SensorReadingSender sensorReadingSender;


    public SensorReadingProducer(SensorReadingSender sensorReadingSender) {
    this.sensorReadingSender = sensorReadingSender;
    }


    public void sendValidReading(int sensor) {
        log.info("Preparazione invio lettura valida per sensore", sensor);

        SensorReadingDTO read = getValidSensorReading(sensor);
        sensorReadingSender.sendReading(read);
    }


    public void sendInvalidHumidityReading(int sensor) {
            log.info("Preparazione invio lettura errata per umidità per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setHumidityPercentage(150);

            sensorReadingSender.sendReading(read);
    }


    public void sendInvalidTemperatureReading(int sensor) {
            log.info("Preparazione invio lettura errata per temperatura per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setTemperature(150d);

            sensorReadingSender.sendReading(read);
    }

 
    
    public void sendInvalidLightReading(int sensor) {
            log.info("Preparazione invio lettura errata per luce per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setLight(-1);

            sensorReadingSender.sendReading(read);
        
    }


    
    public void sendInvalidTimestampReading(int sensor) {
            log.info("Preparazione invio lettura errata per timestamp per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setTimestamp(Instant.parse("2050-11-22T10:30:00Z"));

            
            sensorReadingSender.sendReading(read);
        
    }

    private SensorReadingDTO getValidSensorReading (int sensor) {
        SensorReadingDTO read = new SensorReadingDTO();

        SensorMock mock = SensorsHandler.sensori.get(sensor);

        SensorDTO dto = new SensorDTO(mock.getSensorId(), mock.getPlantId());
        read.setSensorId(dto);
        read.setTimestamp(Instant.now());
        read.setTemperature(ThreadLocalRandom.current().nextDouble(-20d, 45d));
        read.setHumidityPercentage(ThreadLocalRandom.current().nextInt(0,100));
        read.setLight(ThreadLocalRandom.current().nextInt(0,4000)); // non ho idea di quali valori siano appropriati
        return read;
    }

}
