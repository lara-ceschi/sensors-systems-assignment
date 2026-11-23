package com.interview.assignment.sensor_data_persistence.configuration;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Sensor Reading API",
        version = "1.0",
        description = "API per la consultazione delle letture dei sensori"
    )
)
public class SensorReadingAPIConfig {

}
