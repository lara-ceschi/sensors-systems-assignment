package com.interview.assignment.sensors_emulator.scheduling;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.interview.assignment.sensors_emulator.components.SensorReadingProducer;

@Service 
public class SensorsEmulatorTask {

	private static final Logger log = LoggerFactory.getLogger(SensorsEmulatorTask.class);

	private SensorReadingProducer sensorReadingProducer; 

	public SensorsEmulatorTask(SensorReadingProducer sensorReadingProducer) {
		this.sensorReadingProducer = sensorReadingProducer;
	}

	@Value("${sensors.emulator.invalid.read.frequency:5}")
	int invalidReadingFrequency;

	private int readCount = 0;
	

	@Scheduled(fixedRateString = "${sensor.simulator.scheduling.interval:10000}")
	public void scheduledTask() {
		readCount++;
		log.info("Invio lettura numero: {}", readCount);
		
		if (readCount == invalidReadingFrequency) {
			sensorReadingProducer.sendInvalidTimestampReading();
		} if (readCount == invalidReadingFrequency * 2) {
			sensorReadingProducer.sendInvalidTemperatureReading();
		} if (readCount == invalidReadingFrequency * 3) {
			sensorReadingProducer.sendInvalidHumidityReading();
		} if (readCount == invalidReadingFrequency * 4) {
			sensorReadingProducer.sendInvalidLightReading();
			readCount = 0;
		} else {
			sensorReadingProducer.sendValidReading();
		}
	}

}