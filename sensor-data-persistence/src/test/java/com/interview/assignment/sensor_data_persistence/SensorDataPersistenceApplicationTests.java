package com.interview.assignment.sensor_data_persistence;

import java.sql.Timestamp;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.UseMainMethod;

import com.interview.assignment.sensor_data_persistence.components.SensorDataPersistenceProcessor;
import com.interview.assignment.sensor_datatypes.SensorDTO;
import com.interview.assignment.sensor_datatypes.SensorReadingDTO;

@SpringBootTest(useMainMethod = UseMainMethod.ALWAYS, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SensorDataPersistenceApplicationTests {

	@Autowired 
	SensorDataPersistenceProcessor sensorDataPersistenceProcessor;
	
	//@Test
	void contextLoads() {

		SensorDTO sensor = new SensorDTO(1l, 1l);

		SensorReadingDTO read = new SensorReadingDTO();
		read.setSensorId(sensor);
        read.setTimestamp(new Timestamp(System.currentTimeMillis()));
        read.setTemperature(ThreadLocalRandom.current().nextDouble(-20d, 45d));
        read.setHumidityPercentage(ThreadLocalRandom.current().nextInt(0,100));
        read.setLight(ThreadLocalRandom.current().nextInt(0,4000)); // non ho idea di quali valori siano appropriati
       

		sensorDataPersistenceProcessor.convertAndInsert(read);
	}


	
}
