package com.interview.assignment.sensor_data_persistence.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private static final String PACKAGE_DATATYPES = "com.interview.assignment.sensor_datatypes";
    private static final String QUEUE_PERSISTENZA_DATI = "PERSISTENZA_DATI";

   
    @Bean
    public Queue sensorDataPersistenceQueue() {
        return QueueBuilder.durable(QUEUE_PERSISTENZA_DATI)
                .build();
    }

    @Bean
    JacksonJsonMessageConverter converter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter(PACKAGE_DATATYPES);
        return converter;
    }
}
