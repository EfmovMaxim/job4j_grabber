package ru.job4j.solid.srp;

import java.util.List;

public class HTMLOutput implements OutputFormat{
    private StringBuilder text = new StringBuilder();

    @Override
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

    @Override
    public void outputRow(List<String> row) {
        text.append("<tr>").append(System.lineSeparator());
        for (String column : row) {
            text.append("<td>").append(column).append("</td>").append(System.lineSeparator());
        }
        text.append("</tr>").append(System.lineSeparator());
    }

    @Override
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
