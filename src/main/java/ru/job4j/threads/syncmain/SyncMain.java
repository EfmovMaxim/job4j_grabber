package ru.job4j.threads.syncmain;

public class SyncMain {
    public static void main(String[] args) throws Exception {
        Data d = new Data();
        MyThread t1 = new MyThread(d);
        MyThread t2 = new MyThread(d);


        Thread.sleep(3000);
        System.out.println(d.count);
        System.out.println(Data.countSt);
    }
}
