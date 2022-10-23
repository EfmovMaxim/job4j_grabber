package ru.job4j.parking;

import java.util.List;

public interface Parking {
    boolean add(Vehicle vehicle);
    List<Vehicle> getVehicles();
}
