package ru.job4j.gc;

public class User {
    private String name;
    private int id;

    public User(int id, String name)  {
        this.name = name;
        this.id = id;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", id, name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public static void main(String[] args) {
        for (int i = 0; i < 263000; i++) {
            new User(i, "Qq");
            System.out.println(i);
        }
    }
}
