package com.interview.assignment.sensor_controller.components;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbitmq.client.RabbitAmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorCommand;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;
import com.interview.assignment.sensor_datatypes.SensorCommand.COMMAND;


@Component
public class SensorCommandSender {
    private static final Logger log = LoggerFactory.getLogger(SensorCommandSender.class);

    private RabbitAmqpTemplate rabbitTemplate;
    private FanoutExchange exchange; 
   

    public SensorCommandSender(RabbitAmqpTemplate rabbitTemplate, FanoutExchange exchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }


    public void sendRestartCommand(int sensorCount) {
        log.info("Invio comando di riavvio al sensore {}", sensorCount);
        SensorCommand command = new SensorCommand();
        command.setCommand(COMMAND.RESTART);

        String routingKey="sensor"+sensorCount;

        this.rabbitTemplate.convertAndSend(exchange.getName(), routingKey, command);
    }

     public void sendRescheduleCommand(int sensorCount) {
        log.info("Invio comando di rischedulazione al sensore {}", sensorCount);
        SensorCommand command = new SensorCommand();
        command.setCommand(COMMAND.CHANGE_INTERVAL);
        command.setValue(30000); // 30 secondi
        
        String routingKey="sensor"+sensorCount;

        this.rabbitTemplate.convertAndSend(exchange.getName(), routingKey, command);
    }
		

}
