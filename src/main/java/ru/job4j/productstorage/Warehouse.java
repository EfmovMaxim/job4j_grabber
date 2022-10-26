package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Warehouse implements Store {
    private List<Food> foods = new ArrayList<>();
    private Predicate<Double> condition = d -> d > 0.75;

    @Override
    public boolean add(Food food) {
        if (checkCondition(food)) {
            foods.add(food);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCondition(Food food) {
        return condition.test(calcExpRemain(food));
    }

    @Override
    public List<Food> getFoods() {
        return foods.stream().toList();
    }

    @Override
    public void clearFoods() {
        foods.clear();
    }
}
