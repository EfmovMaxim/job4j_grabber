package ru.job4j.parking;

public abstract class Vehicle {
    protected int vehicleSize;
    protected String stateNumber;

    public Vehicle(String stateNumber, int vehicleSize) {
        this.vehicleSize = vehicleSize;
        this.stateNumber = stateNumber;
    }

    public int getVehicleSize() {
        return vehicleSize;
    }

    public String getStateNumber() {
        return stateNumber;
    }
}
