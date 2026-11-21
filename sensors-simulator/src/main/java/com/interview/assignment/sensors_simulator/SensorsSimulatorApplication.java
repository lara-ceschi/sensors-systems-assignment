package com.interview.assignment.sensors_simulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableScheduling
public class SensorsSimulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsSimulatorApplication.class, args);
	}

}
