package ru.job4j.solid.srp;

import java.util.List;

public interface OutputFormat {
    void outputTitle(List<String> columns);
    void outputRow(List<String> row);
    void outputBasement();
    StringBuilder getOutputText();
}
