package com.interview.assignment.sensor_data_processor.components;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_data_processor.services.SensorReadProcessor;
import com.interview.assignment.sensor_datatypes.SensorRead;
import com.rabbitmq.client.Channel;

@Component 
public class SensorReadReceiver {
private static final Logger log = LoggerFactory.getLogger(SensorReadReceiver.class);

   private static final String QUEUE_LETTURE = "LETTURE";

   private final SensorReadProcessor sensorReadProcessor;
  
    public SensorReadReceiver(SensorReadProcessor sensorReadProcessor) {
        this.sensorReadProcessor = sensorReadProcessor;
    }


    @RabbitListener(queues = QUEUE_LETTURE)
    public void receive(SensorRead reading) {
            log.info("Ricevuto messaggio {}", reading);

            boolean readHandled = sensorReadProcessor.handleRead(reading);

            // TODO: logica di retry e gestione degli ack/nack
            log.debug("messaggio elaborato correttamente? {}", readHandled);
    }
    
}
