package ru.job4j.solid.srp;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter, OutputFormat outputFormat, List<String> columns, Function<Double, Double> salaryConvert, Comparator<Employee> comparator);
}
