package ru.job4j.threads.syncmain;

public class MyThread implements Runnable {
    private Data obj;
    private Thread self;

    public static Object monitor = new Object();

    public MyThread(Data d) {
        obj = d;
        self = new Thread(this);
        self.start();
    }

    public void Add() {
        try {
            Thread.sleep(5);
            synchronized (monitor) {
                int n = obj.count;
                n++;

            Thread.sleep(5);
            obj.count = n;
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static void AddStatic() {
        try {
            Thread.sleep(5);
            synchronized (monitor) {
                int n = Data.countSt;
                n++;
                Thread.sleep(5);
                Data.countSt = n;
            }
        } catch (InterruptedException ex) {

        }
    }


    @Override
    public void run() {
        for(int i=0; i<10; i++) Add();
        for(int i=0; i<10; i++) AddStatic();
    }
}
