package com.interview.assignment.sensor_datatypes;

public class SensorCommand {
    private COMMAND command;
    private Integer value;

    public COMMAND getCommand() {
        return command;
    }

    public void setCommand(COMMAND command) {
        this.command = command;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

   public enum COMMAND {
        RESTART,
        CHANGE_INTERVAL
    }
}
