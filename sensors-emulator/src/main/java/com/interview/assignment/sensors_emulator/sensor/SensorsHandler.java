package com.interview.assignment.sensors_emulator.sensor;

import java.util.HashMap;
import java.util.LinkedHashMap;

import org.springframework.stereotype.Service;

import com.interview.assignment.sensor_datatypes.SensorCommand;
import com.interview.assignment.sensors_emulator.scheduling.SensorsEmulatorTaskService;
import com.interview.assignment.sensors_emulator.sensor.SensorMock.STATUS;



@Service 
public class SensorsHandler {
    public static HashMap<Integer, SensorMock> sensori = new LinkedHashMap<>();

    private final SensorsEmulatorTaskService sensorsEmulatorTaskService;

	public SensorsHandler(SensorsEmulatorTaskService sensorsEmulatorTaskService) {
		this.sensorsEmulatorTaskService = sensorsEmulatorTaskService;
	}
   
    public static void init(int sensorNumber, int defaultInterval) {

        for (int i = 0; i<sensorNumber; i++) {
            sensori.put(i, new SensorMock(i+1, i%2, STATUS.STARTED, defaultInterval));
        }
    }


    public boolean handleCommand(SensorCommand command) {
        int destinatario = command.getDestinatario();

        if (sensori.get(destinatario) == null) {
            return false;
        }

        switch(command.getCommand()) {
            case CHANGE_INTERVAL:
                sensori.get(destinatario).setInterval(command.getValue());
                sensorsEmulatorTaskService.changeSchedule(destinatario);
                break;
            case RESTART:
                sensori.get(destinatario).setStatus(STATUS.STARTED);
                break;
            case STOP:
                sensori.get(destinatario).setStatus(STATUS.STOPPED);
                break;
            default:
                break;
        }

        return true;
    }
}
