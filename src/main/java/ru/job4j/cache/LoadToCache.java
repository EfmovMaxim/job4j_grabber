package ru.job4j.cache;

public class LoadToCache implements UserAction{
    private Emulator emulator;
    private Input input;

    public LoadToCache(Emulator emulator, Input input) {
        this.emulator = emulator;
        this.input = input;
    }

    @Override
    public String name() {
        return "Загрузить содержимое файла в кэш";
    }

    @Override
    public boolean execute() {
        AbstractCache cache = emulator.getCache();

        if (cache == null) {
            System.out.println("Для начала укажите директорию");
            return true;
        }
        cache.load(input.askStr("Введите название файла:"));
        return true;
    }
}
