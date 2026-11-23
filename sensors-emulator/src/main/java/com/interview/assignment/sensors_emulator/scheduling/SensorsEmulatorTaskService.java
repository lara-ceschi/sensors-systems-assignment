package com.interview.assignment.sensors_emulator.scheduling;


import java.time.Duration;
import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;

import com.interview.assignment.sensors_emulator.components.SensorReadingProducer;
import com.interview.assignment.sensors_emulator.sensor.SensorMock;
import com.interview.assignment.sensors_emulator.sensor.SensorsHandler;

import jakarta.annotation.PostConstruct;

@Service 
public class SensorsEmulatorTaskService {

	private static final Logger log = LoggerFactory.getLogger(SensorsEmulatorTaskService.class);

	@Value("${sensor.number}")
    private int sensorNumber;

	@Value("${sensor.emulator.scheduling.interval:10}")
    private int schedulingInterval;


	private HashMap<Integer, ScheduledFuture<?>> futuri = new HashMap<>();

	private final SensorReadingProducer sensorReadingProducer;
	private final TaskScheduler scheduler;

	public SensorsEmulatorTaskService(SensorReadingProducer sensorReadingProducer, TaskScheduler scheduler) {
		this.sensorReadingProducer = sensorReadingProducer;
		this.scheduler = scheduler;
	}

	@PostConstruct
    public void startScheduling() {

		for (int i = 0; i < sensorNumber; i++) {
			Integer key = Integer.valueOf(i);

			SensorMock currentSensor = SensorsHandler.sensori.get(key);
			if (currentSensor == null) {
				SensorsHandler.init(sensorNumber, schedulingInterval);
				currentSensor = SensorsHandler.sensori.get(key);
			}
			

			if (currentSensor != null) {
				int secondi = currentSensor.getInterval();
				ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {scheduledTask(key);}, Duration.ofSeconds(secondi));
				futuri.put(key, future);
				log.info("Task schedulato ogni {} secondi", secondi);
			} 
			
		}
    }

	int readCount = 0;

	public void scheduledTask(int sensorId) {
		readCount++;
		log.info("Invio lettura numero: {}", readCount);
		
		if (readCount == 5) {
			sensorReadingProducer.sendInvalidTimestampReading(sensorId);
		} if (readCount == 7) {
			sensorReadingProducer.sendInvalidTemperatureReading(sensorId);
		} if (readCount == 12) {
			sensorReadingProducer.sendInvalidHumidityReading(sensorId);
		} if (readCount == 15) {
			sensorReadingProducer.sendInvalidLightReading(sensorId);
			readCount = 0;
		} else {
			sensorReadingProducer.sendValidReading(sensorId);
		}
	}

	synchronized public void changeSchedule(int key) {
		SensorMock currentSensor = SensorsHandler.sensori.get(key);
		if (currentSensor != null) {
			ScheduledFuture<?> old = futuri.get(key);
			old.cancel(false);

			int secondi = currentSensor.getInterval();
			ScheduledFuture<?> future = scheduler.scheduleAtFixedRate(() -> {scheduledTask(key);}, Duration.ofSeconds(secondi));
			futuri.put(key, future);
			log.info("Task rischedulato ogni {} secondi", secondi);
		}		
	}

}