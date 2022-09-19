package ru.job4j.solid.srp;

import java.text.SimpleDateFormat;
import java.util.List;

public class TXTOutput implements OutputFormat{
    private StringBuilder text = new StringBuilder();

    @Override
    public void outputTitle(List<String> columns) {
        for (String column : columns) {
            text.append(column).append("; ");
        }
        text.append(System.lineSeparator());
    }

    @Override
    public void outputRow(List<String> row) {
        for (String column : row) {
            text.append(column).append(";");
        }
        text.append(System.lineSeparator());

    }

    @Override
    public void outputBasement() {

    }

    @Override
    public StringBuilder getOutputText() {
        return text;
    }
}
