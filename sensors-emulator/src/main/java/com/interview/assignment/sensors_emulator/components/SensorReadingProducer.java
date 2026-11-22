package com.interview.assignment.sensors_emulator.components;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorDTO;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;



@Component  
public class SensorReadingProducer {
private static final Logger log = LoggerFactory.getLogger(SensorReadingProducer.class);

    private List<SensorDTO> sensors = List.of(new SensorDTO(1l,1l), new SensorDTO(2l,2l), new SensorDTO(3l,1l), new SensorDTO(4l,1l));

    private final SensorReadingSender sensorReadingSender;


    public SensorReadingProducer(SensorReadingSender sensorReadingSender) {
    this.sensorReadingSender = sensorReadingSender;
    }


    public void sendValidReading() {
        for(SensorDTO sensor : sensors) {
            log.info("Preparazione invio lettura valida per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            sensorReadingSender.sendReading(read);
        }
    }


    public void sendInvalidHumidityReading() {
        for(SensorDTO sensor : sensors) {
            log.info("Preparazione invio lettura errata per umidità per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setHumidityPercentage(150);

            sensorReadingSender.sendReading(read);
        }
    }


    public void sendInvalidTemperatureReading() {
        for(SensorDTO sensor : sensors) {
            log.info("Preparazione invio lettura errata per temperatura per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setTemperature(150d);

            sensorReadingSender.sendReading(read);
        }
    }


    
    public void sendInvalidLightReading() {
        for(SensorDTO sensor : sensors) {
            log.info("Preparazione invio lettura errata per luce per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setLight(-1);

            sensorReadingSender.sendReading(read);
        }
    }


    
    public void sendInvalidTimestampReading() {
        for(SensorDTO sensor : sensors) {
            log.info("Preparazione invio lettura errata per timestamp per sensore", sensor);

            SensorReadingDTO read = getValidSensorReading(sensor);
            read.setTimestamp(new Timestamp(System.currentTimeMillis() + 10000000));

            
            sensorReadingSender.sendReading(read);
        }
    }

    private SensorReadingDTO getValidSensorReading (SensorDTO sensor) {
        SensorReadingDTO read = new SensorReadingDTO();
        read.setSensorId(sensor);
        read.setTimestamp(new Timestamp(System.currentTimeMillis()));
        read.setTemperature(ThreadLocalRandom.current().nextDouble(-20d, 45d));
        read.setHumidityPercentage(ThreadLocalRandom.current().nextInt(0,100));
        read.setLight(ThreadLocalRandom.current().nextInt(0,4000)); // non ho idea di quali valori siano appropriati
        return read;
    }

}
