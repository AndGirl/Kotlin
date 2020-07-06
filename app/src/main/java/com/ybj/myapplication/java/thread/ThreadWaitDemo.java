package com.ybj.myapplication.java.thread;

/**
 * Created by 杨阳洋 on 2020/6/28.
 */
public class ThreadWaitDemo implements TestDemo {

    private String sharedString;

    private synchronized void initString() {
        sharedString = "uart";
        notifyAll();
    }

    private synchronized void printString() {
        while (sharedString == null) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("println : " + sharedString);
    }

    @Override
    public void runTest() {

        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                printString();
            }
        };
        System.out.println("11111111");
        thread1.start();

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                initString();
            }
        };
        System.out.println("2222222");
        thread2.start();
    }

}
