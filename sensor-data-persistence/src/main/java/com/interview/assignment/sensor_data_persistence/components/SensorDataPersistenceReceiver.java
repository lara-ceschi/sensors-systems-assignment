package com.interview.assignment.sensor_data_persistence.components;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorReadingDTO;

@Component 
public class SensorDataPersistenceReceiver {
private static final Logger log = LoggerFactory.getLogger(SensorDataPersistenceReceiver.class);

   private static final String QUEUE_PERSISTENZA_DATI = "PERSISTENZA_DATI";

   private final SensorDataPersistenceProcessor sensorDataPersistenceProcessor;
  
    public SensorDataPersistenceReceiver(SensorDataPersistenceProcessor sensorDataPersistenceProcessor) {
        this.sensorDataPersistenceProcessor = sensorDataPersistenceProcessor;
    }


    @RabbitListener(queues = QUEUE_PERSISTENZA_DATI)
    public void receive(SensorReadingDTO reading) {
            log.info("Ricevuto messaggio {}", reading);

            try {
                sensorDataPersistenceProcessor.convertAndInsert(reading);
            } catch(Exception e) {
                log.error("Eccezione nell'elaborazione del messaggio o nell'inserimento a db", e);
            }
                log.debug("messaggio elaborato correttamente e dato inserito");
             // TODO: logica di retry e gestione degli ack/nack
    }
    
}
