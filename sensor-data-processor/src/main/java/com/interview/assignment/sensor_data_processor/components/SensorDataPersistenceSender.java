package com.interview.assignment.sensor_data_processor.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbitmq.client.RabbitAmqpTemplate;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorReadingDTO;

import org.springframework.amqp.core.Queue;

@Component  
public class SensorDataPersistenceSender {
private static final Logger log = LoggerFactory.getLogger(SensorDataPersistenceSender.class);

    private RabbitAmqpTemplate template;
    private Queue sensorDataPersistenceQueue;
   

    public SensorDataPersistenceSender(RabbitAmqpTemplate template, Queue sensorDataPersistenceQueue) {
        this.template = template;
        this.sensorDataPersistenceQueue = sensorDataPersistenceQueue;
    }


    public void sendDataToBePersisted(SensorReadingDTO sensorRead) {
        log.info("Invio messaggio {}", sensorRead);
        this.template.convertAndSend(sensorDataPersistenceQueue.getName(), sensorRead);
        // TODO: gestione degli ack e degli errori
    }


}
