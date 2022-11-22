package ru.job4j.threads.rw;

import javax.management.monitor.Monitor;
import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class RW {

    public static void main(String[] args) throws Exception {
        Data d = new Data();

        ExecutorService es = Executors.newFixedThreadPool(5);
        for(int i=0; i<5; i++)
            es.submit(new WorkWData(d));

        TimeUnit.SECONDS.sleep(3);
        es.shutdown();
        es.awaitTermination(10000, TimeUnit.MILLISECONDS);
    }
}

class WorkWData implements Runnable {
    Data obj;
    WorkWData(Data d) {
        obj=d;
    }

    public void run() {
        int n;
        n=obj.read();
        System.out.println("First read"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
        obj.write();
        System.out.println("Write ... "+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
        n=obj.read();
        System.out.println("Second read"+" "+Thread.currentThread().getName()+" "+new Integer(n).toString());
    }
}

class Data {
    private int count=0;
    Monitor monitor = new Monitor() {
        @Override
        public void start() {

        }

        @Override
        public void stop() {

        }
    };
    private ReadWriteLock lock = new ReentrantReadWriteLock();
    private Lock rl = lock.readLock();
    private Lock wl = lock.writeLock();



    public int read(){
        try {
            while (!rl.tryLock()) {
                synchronized (rl) {
                    rl.wait();
                }
            }

            int n = count;
            TimeUnit.MILLISECONDS.sleep(400);
            count=n;
        } catch (InterruptedException ex) {

        } finally {
            rl.unlock();
            synchronized (rl) {
                rl.notify();
            }
        }

        return count;
    }

    public void write(){
        try {
            while (!wl.tryLock()) {
                synchronized (wl) {
                    wl.wait();
                }
            }
            int n = count;
            TimeUnit.MILLISECONDS.sleep(400);
            n++;
            count=n;
        } catch (InterruptedException ex) {

        } finally {
            wl.unlock();
            synchronized (wl) {
                wl.notify();
            }
        }
    }
}
