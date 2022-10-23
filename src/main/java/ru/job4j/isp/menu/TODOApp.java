package ru.job4j.isp.menu;

import java.util.*;

public class TODOApp {

    private Scanner scanner;
    private Menu menu;
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public TODOApp(Menu menu, Scanner scanner) {
        this.scanner = scanner;
        this.menu = menu;
    }

    public void runMenu() {
        var run = true;
        MenuPrinter menuPrinter = new SimpleMenuPrinter();
        int userChoice;
        while (run) {
            showMenu();
            System.out.print("Выберите пункт меню:");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    System.out.print("Введите наименование задачи:");
                    menu.add(Menu.ROOT, scanner.next(), STUB_ACTION);
                    break;
                case 2:
                    System.out.print("Введите наименование подзадачи:");
                    var parent = scanner.next();
                    System.out.print("Введите наименование задачи:");
                    menu.add(parent, scanner.next(), STUB_ACTION);
                    break;
                case 3: break;
                case 4:
                    menuPrinter.print(menu);
                    break;
                case 5:
                    run = false;
                    break;
            }
        }
    }

    private void showMenu() {
        System.out.println("1. Добавить задачу");
        System.out.println("2. Добавить подзадачу");
        System.out.println("3. Выполнить действие");
        System.out.println("4. Вывести меню");
        System.out.println("5. Выйти");
    }

    public static void main(String[] args) {
        TODOApp todoApp = new TODOApp(new SimpleMenu(), new Scanner(System.in));
        todoApp.runMenu();
    }
}
