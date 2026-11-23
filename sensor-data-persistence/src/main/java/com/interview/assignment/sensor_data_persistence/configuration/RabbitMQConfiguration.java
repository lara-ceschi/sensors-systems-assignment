package com.interview.assignment.sensor_data_persistence.configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.interview.assignment.sensor_datatypes.constants.QueueNames;

@Configuration
public class RabbitMQConfiguration {
    private static final String PACKAGE_DATATYPES = "com.interview.assignment.sensor_datatypes";

   
    @Bean
    public Queue sensorDataPersistenceQueue() {
        return QueueBuilder.durable(QueueNames.PERSISTENT_DATA)
                .build();
    }

    @Bean
    JacksonJsonMessageConverter converter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter(PACKAGE_DATATYPES);
        return converter;
    }
}
