package com.interview.assignment.sensor_controller.configuration;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {
    private static final String QUEUE_COMANDI = "COMANDI";
    private static final String EXCHANGE_COMANDI = "exch-comandi";
   
    @Bean
    public Queue sensorReadQueue() {
        return QueueBuilder.durable(QUEUE_COMANDI)
                .build();
    }

    @Bean
	public FanoutExchange exchange() {
		return new FanoutExchange(EXCHANGE_COMANDI);
	}

    
    @Bean
    JacksonJsonMessageConverter converter() {
        JacksonJsonMessageConverter converter = new JacksonJsonMessageConverter();
        return converter;
    }
   
}
