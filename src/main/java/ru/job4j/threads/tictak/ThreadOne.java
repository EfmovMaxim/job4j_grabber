package ru.job4j.threads.tictak;

import javax.management.monitor.Monitor;

public class ThreadOne extends Thread {
    private MyMonitor monitor;

    public ThreadOne(MyMonitor monitor) {
        this.monitor = monitor;
    }

    public void run () {
        try {
            for (int i = 0; i < TicTak.num; i++) {
                System.out.print(1+" - ");
                synchronized (monitor) {
                    monitor.notify();
                    if (i < TicTak.num-1)
                        monitor.wait();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
