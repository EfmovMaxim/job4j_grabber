package ru.job4j.cache;

public class ReadFromCache implements UserAction{
    private Emulator emulator;
    private Input input;

    public ReadFromCache(Emulator emulator, Input input) {
        this.emulator = emulator;
        this.input = input;
    }

    @Override
    public String name() {
        return "Получить содержимое файла из кэша";
    }

    @Override
    public boolean execute() {
        AbstractCache cache = emulator.getCache();

        if (cache == null) {
            System.out.println("Для начала укажите директорию");
            return true;
        }

        System.out.println(cache.get(input.askStr("Введите название файла:")));
        return true;
    }
}
