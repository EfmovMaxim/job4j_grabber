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
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public ReportEngine(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter, OutputFormat outputFormat, List<String> columns, Function<Double, Double> salaryConvert, Comparator<Employee> comparator) {
        outputFormat.outputTitle(columns);

        List<String> row = new ArrayList();
        List<Employee> employees = store.findBy(filter);
        employees.sort(comparator);

        for (Employee employee : employees) {
            if (columns.contains("Name")) {
                row.add(employee.getName());
            }
            if (columns.contains("Hired")) {
                row.add(DATE_FORMAT.format(employee.getHired().getTime()));
            }
            if (columns.contains("Fired")) {
                row.add(DATE_FORMAT.format(employee.getFired().getTime()));
            }
            if (columns.contains("Salary")) {
                row.add(String.valueOf(salaryConvert.apply(employee.getSalary())));
            }
            outputFormat.outputRow(row);
            row.clear();
        }
        outputFormat.outputBasement();

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
                new HTMLOutput(),
                List.of("Name", "Hired", "Fired", "Salary"),
                s -> s * 2,
                new EmployeeDescBySalary()));
    }
}
