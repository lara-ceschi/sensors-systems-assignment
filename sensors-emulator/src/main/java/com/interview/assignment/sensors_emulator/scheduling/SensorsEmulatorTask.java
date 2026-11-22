package com.interview.assignment.sensors_emulator.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.interview.assignment.sensors_emulator.components.SensorReadProducer;

@Service 
public class SensorsEmulatorTask {

	private static final Logger log = LoggerFactory.getLogger(SensorsEmulatorTask.class);

	private SensorReadProducer sensorReadProducer; 

	public SensorsEmulatorTask(SensorReadProducer sensorReadProducer) {
		this.sensorReadProducer = sensorReadProducer;
	}

	@Value("${sensors.emulator.invalid.read.frequency:5}")
	int invalidReadFrequency;

	private int readCount = 0;
	

	@Scheduled(fixedRateString = "${sensor.simulator.scheduling.interval:10000}")
	public void scheduledTask() {
		readCount++;
		log.info("Invio lettura numero: {}", readCount);
		
		if (readCount == invalidReadFrequency) {
			sensorReadProducer.sendInvalidTimestampRead();
		} if (readCount == invalidReadFrequency * 2) {
			sensorReadProducer.sendInvalidTemperatureRead();
		} if (readCount == invalidReadFrequency * 3) {
			sensorReadProducer.sendInvalidHumidityRead();
		} if (readCount == invalidReadFrequency * 4) {
			sensorReadProducer.sendInvalidLightRead();
			readCount = 0;
		} else {
			sensorReadProducer.sendValidRead();
		}
	}

}