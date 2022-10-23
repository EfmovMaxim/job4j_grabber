package ru.job4j.productstorage;

import java.util.Calendar;
import java.util.List;

public interface Store {
    boolean add(Food food);

    boolean checkCondition(Food food);

    List<Food> getFoods();

    default double calcExpRemain(Food food) {
        long now = Calendar.getInstance().getTimeInMillis();
        double expRemain;
        if (food.getExpiryDate() == null) {
            expRemain = 1;
        } else {
            expRemain = (double) (food.getExpiryDate().getTimeInMillis() - now)
                    / (food.getExpiryDate().getTimeInMillis() - food.getCreateDate().getTimeInMillis());
        }
        return expRemain;
    }
}
