package ru.job4j.parking;

import java.util.ArrayList;
import java.util.List;

public class StreetParking implements Parking{
    int SpotsNumber;
    int spotsBalance;
    private List<Vehicle> vehicles = new ArrayList<>();

    public StreetParking(int spotsNumber) {
        SpotsNumber = spotsNumber;
        spotsBalance = spotsNumber;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        var rsl = checkFreeSpot(vehicle);
        if (rsl) {
            vehicles.add(vehicle);
            spotsBalance -= vehicle.getVehicleSize();
        }
        return rsl;
    }

    private boolean checkFreeSpot(Vehicle vehicle) {
        return spotsBalance - vehicle.getVehicleSize() >= 0;
    }

    @Override
    public List<Vehicle> getVehicles() {
        return List.copyOf(vehicles);
    }
}
