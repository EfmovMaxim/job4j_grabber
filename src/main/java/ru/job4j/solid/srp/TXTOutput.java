package ru.job4j.solid.srp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TXTOutput implements OutputFormat{
    private StringBuilder text = new StringBuilder();
    List<String> columns;
    Function<Double, Double> salaryConvert;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public TXTOutput(List<String> columns, Function<Double, Double> salaryConvert) {
        this.columns = columns;
        this.salaryConvert = salaryConvert;
    }

    @Override
    public void giveFormat(List<Employee> employees) {
        List<String> row = new ArrayList();
        outputTitle(columns);
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
            outputRow(row);
            row.clear();
        }
    }

    public void outputTitle(List<String> columns) {
        for (String column : columns) {
            text.append(column).append("; ");
        }
        text.append(System.lineSeparator());
    }

    public void outputRow(List<String> row) {
        for (String column : row) {
            text.append(column).append(";");
        }
        text.append(System.lineSeparator());

    }

    @Override
    public StringBuilder getOutputText() {
        return text;
    }
}
