package ru.job4j.parking;

public class Truck extends Vehicle{

    public Truck(String stateNumber, int vehicleSize) {
        super(stateNumber, vehicleSize);
        if (vehicleSize <= CarSize.CARSIZE) {
            throw new IllegalStateException("Указан некооректный размер для грузовика");
        }
    }
}
