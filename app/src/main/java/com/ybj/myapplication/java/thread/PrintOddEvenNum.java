package com.ybj.myapplication.java.thread;

/**
 * Created by 杨阳洋 on 2020/6/28.
 * 交替打印1-100的奇偶数
 */
public class PrintOddEvenNum implements TestDemo {

    private final int maxNum = 10;
    private volatile int printNum = 1;
    private Object oddObj = new Object();
    private Object evenObj = new Object();

    //基数
    private void printOddNum() {
        synchronized (oddObj) {
            try {
            while (printNum < maxNum) {
                if (printNum % 2 == 0) {
                        oddObj.wait();
                }
                System.out.println("基数：" + printNum);
                printNum++;
                synchronized (evenObj){
                    evenObj.notifyAll();
                }
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //偶数
    private void printEvenNum() {
        synchronized (evenObj) {
            try {
            while (printNum < maxNum) {
                if (printNum % 2 == 1) {
                        evenObj.wait();
                }
                System.out.println("偶数：" + printNum);
                printNum++;
                synchronized (oddObj){
                    oddObj.notifyAll();
                }
            }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void runTest() {
        Thread oddThread = new Thread() {
            @Override
            public void run() {
                printOddNum();
            }
        };
        oddThread.start();

        Thread evenThread = new Thread() {
            @Override
            public void run() {
                printEvenNum();
            }
        };
        evenThread.start();

    }
}
