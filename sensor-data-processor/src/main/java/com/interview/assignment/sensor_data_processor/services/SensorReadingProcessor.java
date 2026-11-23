package com.interview.assignment.sensor_data_processor.services;


import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.assignment.sensor_data_processor.components.SensorDataPersistenceSender;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;


@Service 
public class SensorReadingProcessor {

private static final Logger log = LoggerFactory.getLogger(SensorReadingProcessor.class);
    @Autowired 
    private final SensorDataPersistenceSender sensorDataPersistenceSender;

    public SensorReadingProcessor(SensorDataPersistenceSender sensorDataPersistenceSender) {
        this.sensorDataPersistenceSender = sensorDataPersistenceSender;
    }


    public boolean handleReading(SensorReadingDTO read) {
        boolean inputCorretto = true;
        
        if (read.getSensor() == null) {
            log.error("Lettura con sensore non definito: {}", read);
            inputCorretto = false;
        }

        if (read.getHumidityPercentage() != null && 
             (read.getHumidityPercentage() < 0 || read.getHumidityPercentage() > 100)) {
            log.error("Lettura con valore umidità non valido: {}", read);
            inputCorretto = false;
        }

        if (read.getTemperature() != null && 
             (read.getTemperature() < -20d || read.getTemperature() > +45d)) {
            log.error("Lettura con valore temperatura non valido: {}", read);
            inputCorretto = false;
        }


        if (read.getLight() != null && 
             (read.getLight() < 0 || read.getLight() > 4000)) {
            log.error("Lettura con valore luminosità non valido: {}", read);
            inputCorretto = false;
        }

        if (read.getTimestamp() == null || read.getTimestamp().isAfter(Instant.now())) {
            log.error("Lettura con timestamp non presente o non valido: {}", read);
            inputCorretto = false;
        }

        if (!inputCorretto) {
            // il dato di input non è corretto, non procediamo al salvataggio, ma il messaggio è
            // comunque da considerarsi elaborato
            return true;
        }

        sensorDataPersistenceSender.sendDataToBePersisted(read);
        return true; // se non ci sono eccezioni, il messaggio è da considerarsi elaborato
    }

    
}
