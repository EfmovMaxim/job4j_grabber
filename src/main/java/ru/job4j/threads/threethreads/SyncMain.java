package ru.job4j.threads.threethreads;

public class SyncMain {
    public static void main(String[] args) throws InterruptedException {
        MyThread[] threads = new MyThread[3];
        MyMonitor monitor = new MyMonitor();
        monitor.currentThread = 0;
        for (int i = 0; i < 3; i++) {
            //threads[i] = new MyThread(i, monitor);
        }

        System.out.println();

        MyMonitor monitor2 = new MyMonitor();
        var tr1 = new ThrOne(0, monitor2);
        var tr2 = new ThrTwo(1, monitor2);
        var tr3 = new ThrThree(2, monitor2);

        Thread.sleep(3000);
    }
}
