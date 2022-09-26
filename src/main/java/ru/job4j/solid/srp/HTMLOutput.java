package ru.job4j.solid.srp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class HTMLOutput implements OutputFormat{
    private StringBuilder text = new StringBuilder();
    List<String> columns;
    Function<Double, Double> salaryConvert;
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    public HTMLOutput(List<String> columns, Function<Double, Double> salaryConvert) {
        this.columns = columns;
        this.salaryConvert = salaryConvert;
    }

    @Override
    public void giveFormat(List<Employee> employees) {
        List<String> row = new ArrayList();

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
        text.append("<html lang = \"ru\">")
                .append(System.lineSeparator())
                .append("<head>")
                .append(System.lineSeparator())
                .append("<meta charset=\"UTF-8\">")
                .append(System.lineSeparator())
                .append("<title>Отчет по сотрудникам</title>")
                .append(System.lineSeparator())
                .append("</head>")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("<table>")
                .append(System.lineSeparator())
                .append("<tr>")
                .append(System.lineSeparator());

        for (String column : columns) {
            text.append("<th>").append(column).append("</th>").append(System.lineSeparator());
        }
        text.append("</tr>").append(System.lineSeparator());
    }

    public void outputRow(List<String> row) {
        text.append("<tr>").append(System.lineSeparator());
        for (String column : row) {
            text.append("<td>").append(column).append("</td>").append(System.lineSeparator());
        }
        text.append("</tr>").append(System.lineSeparator());
    }

    public void outputBasement() {
        text.append("</table>")
                .append(System.lineSeparator())
                .append("</body>")
                .append(System.lineSeparator())
                .append("</html>");
    }

    @Override
    public StringBuilder getOutputText() {
        return text;
    }
}
