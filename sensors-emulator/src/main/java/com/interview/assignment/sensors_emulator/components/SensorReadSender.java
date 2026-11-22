package com.interview.assignment.sensors_emulator.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbitmq.client.RabbitAmqpTemplate;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorRead;


@Component
public class SensorReadSender {
    private static final Logger log = LoggerFactory.getLogger(SensorReadSender.class);

    private RabbitAmqpTemplate rabbitTemplate;
    private Queue sensorReadQueue;
   

    public SensorReadSender(RabbitAmqpTemplate rabbitTemplate, Queue sensorReadQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.sensorReadQueue = sensorReadQueue;
    }


    public void sendRead(SensorRead sensorRead) {
        log.info("Invio messaggio {}", sensorRead);
        this.rabbitTemplate.convertAndSend(sensorReadQueue.getName(), sensorRead);
        // TODO: gestione degli ack e degli errori
    }


}
