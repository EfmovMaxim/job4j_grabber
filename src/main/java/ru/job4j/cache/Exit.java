package ru.job4j.cache;

public class Exit implements UserAction {
    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute() {
        return false;
    }
}
