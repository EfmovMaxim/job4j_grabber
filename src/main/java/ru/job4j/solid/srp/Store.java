package ru.job4j.solid.srp;

import java.util.List;
import java.util.function.Predicate;

public interface Store {
    void add(Employee em);
    List<Employee> findBy(Predicate<Employee> filter);
}
