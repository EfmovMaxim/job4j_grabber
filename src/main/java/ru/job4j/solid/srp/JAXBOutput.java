package ru.job4j.solid.srp;

import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.function.Function;

public class JAXBOutput implements OutputFormat{
    private StringBuilder text = new StringBuilder();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd:MM:yyyy HH:mm");

    @Override
    public void giveFormat(List<Employee> employees) {
        var lib = new GsonBuilder().create();
        text.append(lib.toJson(employees));
    }

    @Override
    public StringBuilder getOutputText() {
        return text;
    }

}
