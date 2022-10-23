package ru.job4j.parking;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatException;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

public class StreetParkingTest {

    @Test
    public void when2cars1truck() {
        Vehicle car1 = new Car("p025pp178");
        Vehicle car2 = new Car("e093ee178");
        Vehicle truck1 = new Truck("o455kk178", 3);
        Parking parking = new StreetParking(5);

        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);

        assertThat(parking.getVehicles()).isEqualTo(List.of(car1, car2, truck1));
    }

    @Test
    public void when2cars2trucks() {
        Vehicle car1 = new Car("p025pp178");
        Vehicle car2 = new Car("e093ee178");
        Vehicle truck1 = new Truck("o455kk178", 3);
        Vehicle truck2 = new Truck("c899cc178", 3);

        Parking parking = new StreetParking(5);

        parking.add(car1);
        parking.add(car2);
        parking.add(truck1);
        parking.add(truck2);

        assertThat(parking.getVehicles()).isEqualTo(List.of(car1, car2, truck1));
    }

    @Test
    public void whenIncorrectTruckSize() {
        assertThrows(IllegalStateException.class, () -> {
            new Truck("c899cc178", 1);
        });
    }
}