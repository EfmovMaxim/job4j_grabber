package ru.job4j.productstorage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Shop implements Store {
    private List<Food> foods = new ArrayList<>();
    private Predicate<Double> condition = d -> d < 0.75 && d > 0.0;
    private Predicate<Double> conditionDiscount = d -> d < 0.25 && d > 0.0;

    @Override
    public boolean add(Food food) {
        if (checkCondition(food)) {
            foods.add(food);
            if (checkConditionDiscount(food)) {
                food.setPrice(food.getPrice() * (1.0 - food.getDiscount() / 100.0));
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean checkCondition(Food food) {
        return condition.test(calcExpRemain(food));
    }

    public boolean checkConditionDiscount(Food food) {
        return conditionDiscount.test(calcExpRemain(food));
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
