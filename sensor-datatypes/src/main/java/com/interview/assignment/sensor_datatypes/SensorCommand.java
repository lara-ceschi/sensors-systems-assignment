package com.interview.assignment.sensor_datatypes;

public class SensorCommand {
    private int destinatario;
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
        CHANGE_INTERVAL,
        STOP
    }

   public int getDestinatario() {
    return destinatario;
   }

   public void setDestinatario(int destinatario) {
    this.destinatario = destinatario;
   }

   @Override
   public String toString() {
    return "SensorCommand [destinatario=" + destinatario + ", command=" + command + ", value=" + value + "]";
   }

   
}
