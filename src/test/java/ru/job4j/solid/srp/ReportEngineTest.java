package ru.job4j.solid.srp;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.job4j.solid.srp.ReportEngine.DATE_FORMAT;

class ReportEngineTest {
    @Test
    public void whenOldGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        store.add(worker1);
        Employee worker2 = new Employee("Petr", now, now, 200);
        store.add(worker2);
        Report engine = new ReportEngine(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary; ")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(";")
                .append(DATE_FORMAT.format(worker2.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker2.getFired().getTime())).append(";")
                .append(worker2.getSalary() * 2).append(";")
                .append(System.lineSeparator())
                .append(worker1.getName()).append(";")
                .append(DATE_FORMAT.format(worker1.getHired().getTime())).append(";")
                .append(DATE_FORMAT.format(worker1.getFired().getTime())).append(";")
                .append(worker1.getSalary() * 2).append(";")
                .append(System.lineSeparator());
        assertEquals(expect.toString(), engine.generate(em -> true,
                new TXTOutput(),
                List.of("Name", "Hired", "Fired", "Salary"),
                s -> s * 2,
                new EmployeeDescBySalary()));
    }
}