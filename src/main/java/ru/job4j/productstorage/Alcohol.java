package ru.job4j.productstorage;

import java.util.Calendar;

public class Alcohol extends Food{

    public Alcohol(String name, Calendar createDate, double price, double discount) {
        super(name, createDate, null, price, discount);
    }
}
