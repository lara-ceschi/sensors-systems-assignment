package com.interview.assignment.sensors_emulator.components;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.interview.assignment.sensor_datatypes.SensorCommand;
import com.interview.assignment.sensor_datatypes.constants.QueueNames;
import com.interview.assignment.sensors_emulator.sensor.SensorsHandler;

@Component 
public class SensorCommandReceiver {
private static final Logger log = LoggerFactory.getLogger(SensorCommandReceiver.class);

    private final SensorsHandler sensorsHandler;

    public SensorCommandReceiver(SensorsHandler sensorsHandler) {
        this.sensorsHandler = sensorsHandler;
    }

    

    @RabbitListener(queues = QueueNames.COMANDI)
    public void receive(SensorCommand command) {
            log.info("Ricevuto messaggio {}", command);

            boolean readHandled = sensorsHandler.handleCommand(command);

            // TODO: logica di retry e gestione degli ack/nack
            log.debug("messaggio elaborato correttamente? {}", readHandled);
    }

    

    
}
