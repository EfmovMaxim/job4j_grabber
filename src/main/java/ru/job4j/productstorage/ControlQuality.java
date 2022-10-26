package ru.job4j.productstorage;

import java.util.*;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void allocateInStock(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

    public void resort() {
        List<Food> foods = new ArrayList<>();
        for (Store store: stores) {
            foods.addAll(store.getFoods());
            store.clearFoods();
        }

        for (Food food : foods) {
            allocateInStock(food);
        }

    }

    public List<Store> getStores() {
        return stores;
    }

    public static void main(String[] args) {
        Calendar now = Calendar.getInstance();
        Food apple = new Food("Apple", now,
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1), 100, 5);
        Food bananas = new Food("Bananas", new GregorianCalendar(2022, Calendar.JUNE, 1),
                new GregorianCalendar(2023, Calendar.FEBRUARY, 1), 100, 5);
        Food garlic = new Food("Garlic", new GregorianCalendar(2022, Calendar.JANUARY, 1),
                new GregorianCalendar(2022, Calendar.OCTOBER, 1), 100, 5);
        Food bread = new Food("Bread", new GregorianCalendar(2022, Calendar.JANUARY, 1),
                new GregorianCalendar(2022, Calendar.JUNE, 1), 100, 5);

        Food beer = new Alcohol("Beer", new GregorianCalendar(2022, Calendar.FEBRUARY, 1), 100, 5);

        ControlQuality controlQuality = new ControlQuality(List.of(new Shop(), new Warehouse(), new Trash()));
        controlQuality.allocateInStock(apple);
        controlQuality.allocateInStock(bananas);
        controlQuality.allocateInStock(garlic);
        controlQuality.allocateInStock(bread);
        controlQuality.allocateInStock(beer);

        for (Store store : controlQuality.stores) {
            System.out.println(store.toString());
            store.getFoods().forEach(System.out::println);
        }


        bananas.setExpiryDate(new GregorianCalendar(2022, Calendar.SEPTEMBER, 1));
        controlQuality.resort();
        System.out.println("---После перераспределения---");

        for (Store store : controlQuality.stores) {
            System.out.println(store.toString());
            store.getFoods().forEach(System.out::println);
        }

    }

}
