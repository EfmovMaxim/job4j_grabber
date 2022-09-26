package ru.job4j.solid.srp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ReportEngine implements Report {

    private Store store;


    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter, OutputFormat outputFormat, Comparator<Employee> comparator) {
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);
        try {
            outputFormat.giveFormat(employees);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return outputFormat.getOutputText().toString();
    }

    public static void main(String[] args) {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        System.out.println(engine.generate(em -> true,
                new HTMLOutput(List.of("Name", "Hired", "Fired", "Salary"), s -> s * 2),
                new EmployeeDescBySalary()));

        System.out.println(engine.generate(em -> true,
                new JAXBOutput(),
                new EmployeeDescBySalary()));

        System.out.println(engine.generate(em -> true,
                new XMLOutput(),
                new EmployeeDescBySalary()));
    }
}
