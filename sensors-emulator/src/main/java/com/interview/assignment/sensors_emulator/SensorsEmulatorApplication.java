package com.interview.assignment.sensors_emulator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class SensorsEmulatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SensorsEmulatorApplication.class, args);
	}

}
