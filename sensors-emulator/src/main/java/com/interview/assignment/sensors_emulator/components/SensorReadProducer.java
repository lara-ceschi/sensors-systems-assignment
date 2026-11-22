package com.interview.assignment.sensors_emulator.components;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.Sensor;
import com.interview.assignment.sensor_datatypes.SensorRead;



@Component  
public class SensorReadProducer {
private static final Logger log = LoggerFactory.getLogger(SensorReadProducer.class);

    private List<Sensor> sensors = List.of(new Sensor(1,1), new Sensor(1,2), new Sensor(2,1), new Sensor(2,3));

    private final SensorReadSender sensorReadSender;


    public SensorReadProducer(SensorReadSender sensorReadSender) {
    this.sensorReadSender = sensorReadSender;
    }


    public void sendValidRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura valida per sensore", sensor);

            SensorRead read = getValidSensorRead(sensor);
            sensorReadSender.sendRead(read);
        }
    }


    public void sendInvalidHumidityRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per umidità per sensore", sensor);

            SensorRead read = getValidSensorRead(sensor);
            read.setHumidityPercentage(150);

            sensorReadSender.sendRead(read);
        }
    }


    public void sendInvalidTemperatureRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per temperatura per sensore", sensor);

            SensorRead read = getValidSensorRead(sensor);
            read.setTemperature(150d);

            sensorReadSender.sendRead(read);
        }
    }


    
    public void sendInvalidLightRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per luce per sensore", sensor);

            SensorRead read = getValidSensorRead(sensor);
            read.setLight(-1);

            sensorReadSender.sendRead(read);
        }
    }


    
    public void sendInvalidTimestampRead() {
        for(Sensor sensor : sensors) {
            log.info("Preparazione invio lettura errata per timestamp per sensore", sensor);

            SensorRead read = getValidSensorRead(sensor);
            read.setTimestamp(new Timestamp(System.currentTimeMillis() + 10000000));

            
            sensorReadSender.sendRead(read);
        }
    }

    private SensorRead getValidSensorRead (Sensor sensor) {
        SensorRead read = new SensorRead();
        read.setSensorId(sensor);
        read.setTimestamp(new Timestamp(System.currentTimeMillis()));
        read.setTemperature(ThreadLocalRandom.current().nextDouble(-20d, 45d));
        read.setHumidityPercentage(ThreadLocalRandom.current().nextInt(0,100));
        read.setLight(ThreadLocalRandom.current().nextInt(0,4000)); // non ho idea di quali valori siano appropriati
        return read;
    }

}
