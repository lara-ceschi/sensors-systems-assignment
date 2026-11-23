package com.interview.assignment.sensors_emulator.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.interview.assignment.sensor_datatypes.constants.QueueNames;

@Configuration
public class RabbitMQConfiguration {
   
    @Bean
    public Queue sensorCommandQueue() {
        return QueueBuilder.durable(QueueNames.COMANDI)
                .maxPriority(3)
                .build();
    }

    @Bean
    public Queue sensorReadingQueue() {
        return QueueBuilder.durable(QueueNames.LETTURA)
                .build();
    }

    
    @Bean
    JacksonJsonMessageConverter converter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();
        return converter;
    }
   
}
