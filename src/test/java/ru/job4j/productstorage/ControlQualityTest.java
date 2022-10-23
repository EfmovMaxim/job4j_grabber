package ru.job4j.productstorage;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;

public class ControlQualityTest {

    @Test
    public void when3FoodsInEvenStore() {
        Calendar now = Calendar.getInstance();
        Food bananas = new Food("Bananas", new GregorianCalendar(2022, Calendar.JUNE, 1),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1), 100, 5);
        Food garlic = new Food("Garlic", new GregorianCalendar(2022, Calendar.JANUARY, 1),
                new GregorianCalendar(2022, Calendar.OCTOBER, 1), 100, 5);
        Food beer = new Alcohol("Beer", new GregorianCalendar(2022, Calendar.FEBRUARY, 1), 100, 5);

        var shop = new Shop();
        var warehouse = new Warehouse();
        var trash = new Trash();

        ControlQuality controlQuality = new ControlQuality(List.of(shop, warehouse, trash));
        controlQuality.allocateInStock(bananas);
        controlQuality.allocateInStock(garlic);
        controlQuality.allocateInStock(beer);

        assertThat(shop.getFoods()).isEqualTo(List.of(bananas));
        assertThat(warehouse.getFoods()).isEqualTo(List.of(beer));
        assertThat(trash.getFoods()).isEqualTo(List.of(garlic));
    }

}