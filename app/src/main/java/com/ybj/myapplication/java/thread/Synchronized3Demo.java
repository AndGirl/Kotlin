package com.ybj.myapplication.java.thread;

/**
 * Created by 杨阳洋 on 2020/6/24.
 */
public class Synchronized3Demo implements TestDemo{

    private int x = 0;
    private int y = 0;
    private String name;
    private final Object monitor1 = new Object();
    private final Object monitor2 = new Object();

    private synchronized void count(int newValue){
        synchronized (monitor1){
            x = newValue;
            y = newValue;
        }
    }

    private synchronized void minus(int delta){
        x -= delta;
        y -= delta;
    }

    public synchronized void setName(String name) {
        synchronized (monitor2){
            this.name = name;
        }
    }

    @Override
    public void runTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 1_000_000;i++){
                    count(i);
                    setName("1111");
                }
                System.out.println("final x from 1 : " + x);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0 ; i < 1_000_000;i++){
                    count(i);
                    setName("2222");
                }
                System.out.println("final x from 2 : " + x);
            }
        }).start();
    }
}
