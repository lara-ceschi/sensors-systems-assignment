package com.interview.assignment.sensor_controller.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.interview.assignment.sensor_controller.components.SensorCommandSender;


@Service 
public class SensorCommandTask {

	private static final Logger log = LoggerFactory.getLogger(SensorCommandTask.class);

	private final SensorCommandSender sensorCommandSender;

	

	public SensorCommandTask(SensorCommandSender sensorCommandSender) {
		this.sensorCommandSender = sensorCommandSender;
	}


	private int commandCount = 0;
	private int sensorCount = 0;
	
	@Value("${sensor.number}")
	private int maxSensor;
	

	@Scheduled(fixedRateString = "${sensor.command.scheduling.interva:60000}")
	public void scheduledTask() {
		
		if (commandCount % 2 == 0) {
			sensorCommandSender.sendRestartCommand(sensorCount);
		} else {
			sensorCommandSender.sendRescheduleCommand(sensorCount);
		}

		commandCount++;
		sensorCount = (sensorCount + 1) % maxSensor;
	}

}