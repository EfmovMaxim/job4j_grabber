package ru.job4j.cache;

public class SetDir implements UserAction {
    private Input input;
    private Emulator emulator;

    public SetDir(Emulator emulator, Input input) {
        this.input = input;
        this.emulator = emulator;
    }

    @Override
    public String name() {
        return "Указать кэшируемую директорию";
    }

    @Override
    public boolean execute() {
        emulator.setCache(new DirFileCache(input.askStr("Укажите каэшируемую директорию:")));
        return true;
    }
}
