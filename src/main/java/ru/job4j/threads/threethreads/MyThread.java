package ru.job4j.threads.threethreads;

public class MyThread implements Runnable{
    private int threadNum;
    private Thread self;
    private MyMonitor monitor;


    public MyThread(int threadNum, MyMonitor monitor) throws InterruptedException {
        this.threadNum = threadNum;
        this.monitor = monitor;
        self = new Thread(this);
        Thread.sleep(threadNum);
        self.start();
    }

    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            while (monitor.currentThread != threadNum) {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                    }
                    catch(InterruptedException e) {
                        e.printStackTrace();
                    }
                 }
            }

            System.out.print(threadNum + " (" + i +") - ");
            synchronized (monitor) {
                monitor.currentThread = (threadNum + 1) % 3;
                monitor.notify();
            }
        }
    }
}
