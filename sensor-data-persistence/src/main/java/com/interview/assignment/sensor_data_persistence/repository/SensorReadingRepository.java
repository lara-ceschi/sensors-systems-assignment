package com.interview.assignment.sensor_data_persistence.repository;

import com.interview.assignment.sensor_data_persistence.entities.SensorReading;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.time.Instant;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;



public interface SensorReadingRepository extends ReactiveCrudRepository<SensorReading, Long> {
    
    public Mono<SensorReading> findFirst1BySensorIdOrderByTimestampDesc(Long sensorId);

    public Flux<SensorReading> findBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(Long sensorId, Instant tsFrom);

}
