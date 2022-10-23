package ru.job4j.isp.menu;

import java.util.StringJoiner;
import java.util.stream.IntStream;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        menu.forEach(i -> System.out.println(String.format("%s%s %s",
                " ".repeat((int) i.getNumber().chars().filter(ch -> ch == '.').count()),
                i.getNumber(), i.getName())));
    }
}
