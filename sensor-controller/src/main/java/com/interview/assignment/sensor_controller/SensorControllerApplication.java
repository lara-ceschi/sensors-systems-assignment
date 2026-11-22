package com.interview.assignment.sensor_controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class SensorControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorControllerApplication.class, args);
	}

}
