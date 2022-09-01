package ru.job4j.cache;

import java.util.List;
import java.util.Scanner;

public class Emulator {

    private String cacheDir;
    private AbstractCache cache;

    private void ShowMenu(List<UserAction> actions) {
        int i = 1;
        for (UserAction action : actions) {
            System.out.println(i + ". " + action.name());
            i++;
        }
    }

    public void init(Input input, List<UserAction> actions) {
        boolean run = true;
        int choice;

        while (run) {
            ShowMenu(actions);
            choice = input.askInt("Выберите пункт меню:");
            if (choice > 0 && choice <= actions.size()) {
                run = actions.get(choice - 1).execute();
            }
        }
    }

    public String getCacheDir() {
        return cacheDir;
    }

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    public AbstractCache getCache() {
        return cache;
    }

    public void setCache(AbstractCache cache) {
        this.cache = cache;
    }

    public static void main(String[] args) {
        Emulator emulator = new Emulator();
        Input input = new ConsoleInput();
        emulator.init(input, List.of(new LoadToCache(emulator, input), new ReadFromCache(emulator, input),
                new SetDir(emulator, input), new Exit()));
    }
}
