package com.interview.assignment.sensor_data_persistence.dao;

import com.interview.assignment.sensor_data_persistence.entities.SensorReading;

import org.springframework.data.repository.CrudRepository;


public interface SensorReadingServiceDAO extends CrudRepository<SensorReading, Long> {

}
