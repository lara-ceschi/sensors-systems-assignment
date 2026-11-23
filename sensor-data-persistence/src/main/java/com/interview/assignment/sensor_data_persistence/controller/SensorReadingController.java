package com.interview.assignment.sensor_data_persistence.controller;


import java.time.Instant;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.interview.assignment.sensor_data_persistence.entities.SensorReading;
import com.interview.assignment.sensor_data_persistence.repository.SensorReadingRepository;

import io.swagger.v3.oas.annotations.Operation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController 
@RequestMapping("/readings")
public class SensorReadingController {
    private final SensorReadingRepository sensorReadingRepository;

    public SensorReadingController(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    @GetMapping("/lastRead")
    @Operation(description = "Restituisce l'ultima lettura di un sensore")
    public Mono<SensorReading> lastRead(@RequestParam("sensorId") Long sensorId) {
        return sensorReadingRepository.findFirst1BySensorIdOrderByTimestampDesc(sensorId);
    }

    @GetMapping("/historicRead")
    @Operation(description = "Restituisce la serie storica delle letture di un sensore a partire da un certo momento")
    public Flux<SensorReading> historicRead(@RequestParam("sensorId") Long sensorId, @RequestParam("tsFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Instant tsFrom) {
        return sensorReadingRepository.findBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(sensorId, tsFrom);
    }

    @GetMapping("/all")
    @Operation(description = "Restituisce tutte le letture di tutti i sensori")
    public Flux<SensorReading> all() {
        return sensorReadingRepository.findAll();
    }
    
}
