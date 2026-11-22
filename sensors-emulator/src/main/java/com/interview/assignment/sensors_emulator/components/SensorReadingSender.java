package com.interview.assignment.sensors_emulator.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbitmq.client.RabbitAmqpTemplate;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorReadingDTO;


@Component
public class SensorReadingSender {
    private static final Logger log = LoggerFactory.getLogger(SensorReadingSender.class);

    private RabbitAmqpTemplate rabbitTemplate;
    private Queue sensorReadingQueue;
   

    public SensorReadingSender(RabbitAmqpTemplate rabbitTemplate, Queue sensorReadingQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.sensorReadingQueue = sensorReadingQueue;
    }


    public void sendReading(SensorReadingDTO sensorReading) {
        log.info("Invio messaggio {}", sensorReading);
        this.rabbitTemplate.convertAndSend(sensorReadingQueue.getName(), sensorReading);
        // TODO: gestione degli ack e degli errori
    }


}
