package com.ybj.myapplication.java.thread;

/**
 * Created by 杨阳洋 on 2020/6/28.
 */
public class ThreadInterfactionDemo implements TestDemo {
    @Override
    public void runTest() {

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                for (int i = 0; i < 1_000_000; i++) {
//                    if(isInterrupted()) {
//                        return;
//                    }
                    if(Thread.interrupted()) {
                        return;
                    }
                    System.out.println("number: " + i);
                }
            }
        };
        thread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //thread.stop();
        thread.interrupt();

    }
}
