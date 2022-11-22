package ru.job4j.threads.tictak;

public class TicTak {
    public static int num=20;

    public static void main(String[] args) {
        MyMonitor monitor = new MyMonitor();

        ThreadOne thr1 = new ThreadOne(monitor);
        ThreadTwo thr2 = new ThreadTwo(monitor);

        thr1.start();
        thr2.getThread().start();

        try {
            thr1.join();
            thr2.getThread().join();
        }
        catch (InterruptedException e) { e.printStackTrace();}
    }
}
