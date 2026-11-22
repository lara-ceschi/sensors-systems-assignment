package com.interview.assignment.sensor_data_persistence.repository;

import com.interview.assignment.sensor_data_persistence.entities.SensorReading;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface SensorReadingRepository extends CrudRepository<SensorReading, Long> {
    
    public SensorReading findFirst1BySensorIdOrderByTimestampDesc(Long sensorId);

    public List<SensorReading> findBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(Long sensorId, Timestamp tsFrom);

}
