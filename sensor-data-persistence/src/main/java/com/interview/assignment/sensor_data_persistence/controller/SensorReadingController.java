package com.interview.assignment.sensor_data_persistence.controller;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.interview.assignment.sensor_data_persistence.entities.SensorReading;
import com.interview.assignment.sensor_data_persistence.repository.SensorReadingRepository;


@RestController 
@RequestMapping("/readings")
public class SensorReadingController {
    private final SensorReadingRepository sensorReadingRepository;

    public SensorReadingController(SensorReadingRepository sensorReadingRepository) {
        this.sensorReadingRepository = sensorReadingRepository;
    }

    @GetMapping("/lastRead")
    public SensorReading lastRead(@RequestParam("sensorId") Long sensorId) {
        return sensorReadingRepository.findFirst1BySensorIdOrderByTimestampDesc(sensorId);
    }

    @GetMapping("/historicRead")
    public List<SensorReading> historicRead(@RequestParam("sensorId") Long sensorId, @RequestParam("tsFrom") Timestamp tsFrom) {
        return sensorReadingRepository.findBySensorIdAndTimestampGreaterThanOrderByTimestampDesc(sensorId, tsFrom);
    }

}
