package com.interview.assignment.sensor_controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbitmq.client.RabbitAmqpTemplate;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorCommand;
import com.interview.assignment.sensor_datatypes.SensorCommand.COMMAND;
import org.springframework.amqp.core.Queue;


@Component
public class SensorCommandSender {
    private static final Logger log = LoggerFactory.getLogger(SensorCommandSender.class);

    private RabbitAmqpTemplate rabbitTemplate;
    private Queue queue; 
   

    public SensorCommandSender(RabbitAmqpTemplate rabbitTemplate, Queue queue) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
    }


    public void sendRestartCommand(int sensorCount) {
        log.info("Invio comando di riavvio al sensore {}", sensorCount);
        SensorCommand command = new SensorCommand();
        command.setCommand(COMMAND.RESTART);
        command.setDestinatario(sensorCount);

        this.rabbitTemplate.convertAndSend(queue.getName(), command, message -> {
            message.getMessageProperties().setPriority(2);
            return message;
        });
    }

     public void sendRescheduleCommand(int sensorCount) {
        log.info("Invio comando di rischedulazione al sensore {}", sensorCount);
        SensorCommand command = new SensorCommand();
        command.setDestinatario(sensorCount);
        command.setCommand(COMMAND.CHANGE_INTERVAL);
        command.setValue(30); // 30 secondi
        
        this.rabbitTemplate.convertAndSend(queue.getName(), command, message -> {
            message.getMessageProperties().setPriority(1);
            return message;
        });
    }

     public void sendStopCommand(int sensorCount) {
        log.info("Invio comando di stop al sensore {}", sensorCount);
        SensorCommand command = new SensorCommand();
        command.setDestinatario(sensorCount);
        command.setCommand(COMMAND.STOP);


        this.rabbitTemplate.convertAndSend(queue.getName(), command, message -> {
            message.getMessageProperties().setPriority(3);
            return message;
        });
    }
		

}
