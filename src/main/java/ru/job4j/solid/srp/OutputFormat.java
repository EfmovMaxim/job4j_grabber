package ru.job4j.solid.srp;

import java.util.List;

public interface OutputFormat {
    void giveFormat(List<Employee> employees) throws Exception;
    StringBuilder getOutputText();
}
