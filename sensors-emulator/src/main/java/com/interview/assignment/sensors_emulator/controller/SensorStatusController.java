package com.interview.assignment.sensors_emulator.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.assignment.sensors_emulator.sensor.SensorMock;
import com.interview.assignment.sensors_emulator.sensor.SensorsHandler;

import io.swagger.v3.oas.annotations.Operation;



@RestController 
@RequestMapping("/sensors")
public class SensorStatusController {


    @GetMapping("/all")
    @Operation(summary = "Restituisce lo stato di tutti i sensori")
    public Map<Integer,SensorMock> all() {
        return SensorsHandler.sensori;
    }
    
}
