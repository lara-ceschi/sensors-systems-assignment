package com.interview.assignment.sensors_simulator.scheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service 
public class SensorsSimulatorTask {

	private static final Logger log = LoggerFactory.getLogger(SensorsSimulatorTask.class);


	@Scheduled(fixedRateString = "${sensor.simulator.scheduling.interval:10000}")
	public void scheduledTask() {

	}

}