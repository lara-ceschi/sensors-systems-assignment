package com.interview.assignment.sensors_emulator.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private static final String QUEUE_LETTURE = "LETTURE";
   
    @Bean
    public Queue sensorReadQueue() {
        return QueueBuilder.durable(QUEUE_LETTURE)
                .build();
    }

    
    @Bean
    JacksonJsonMessageConverter converter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();
        return converter;
    }
   
}
