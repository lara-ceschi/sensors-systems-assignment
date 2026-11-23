package com.interview.assignment.sensor_data_processor.components;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_data_processor.services.SensorReadingProcessor;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;
import com.interview.assignment.sensor_datatypes.constants.QueueNames;

@Component 
public class SensorReadingReceiver {
private static final Logger log = LoggerFactory.getLogger(SensorReadingReceiver.class);


   private final SensorReadingProcessor sensorReadingProcessor;
  
    public SensorReadingReceiver(SensorReadingProcessor sensorReadingProcessor) {
        this.sensorReadingProcessor = sensorReadingProcessor;
    }


    @RabbitListener(queues = QueueNames.LETTURA)
    public void receive(SensorReadingDTO reading) {
            log.info("Ricevuto messaggio {}", reading);

            boolean readHandled = sensorReadingProcessor.handleReading(reading);

            // TODO: logica di retry e gestione degli ack/nack
            log.debug("messaggio elaborato correttamente? {}", readHandled);
    }
    
}
