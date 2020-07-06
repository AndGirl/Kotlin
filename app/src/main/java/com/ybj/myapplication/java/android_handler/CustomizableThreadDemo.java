package com.ybj.myapplication.java.android_handler;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.ybj.myapplication.java.thread.TestDemo;

/**
 * Created by 杨阳洋 on 2020/6/28.
 * 1.循环执行任务
 * 2.外部添加任务并且置空
 * 3.退出循环
 *
 * ==================
 * HandlerThread
 */
public class CustomizableThreadDemo implements TestDemo {

    private CustomizableThread thread = new CustomizableThread();
    static ThreadLocal<Integer> threadNum = new ThreadLocal<>();

    public static void main(String[] args) {
        final Handler handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
            }
        };
        new Thread(){
            @Override
            public void run() {
                super.run();
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                    }
                });
            }
        }.start();
        Message message = new Message();
        message.arg1 = 1;
        message.arg2 = 2;
        message.obj = "13";
        message.getCallback();
        handler.dispatchMessage(message);

        //ThreadLocal:线程间不共享的变量
        new Thread(){
            @Override
            public void run() {
                threadNum.set(1);
                threadNum.get();//1
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                threadNum.set(2);
                threadNum.get();//2
            }
        }.start();
        android.os.Looper myLooper = android.os.Looper.myLooper();

//        new ThreadWaitDemo().runTest();

    }

    @Override
    public void runTest() {
        thread.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        thread.quit();
//        thread.setTask(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("HaHaHa");
//            }
//        });
    }

    class CustomizableThread extends Thread{
        private Looper looper;

        @Override
        public void run() {
            looper.loop();
        }
    }

    class Looper {
        Runnable task;//MessageQueue
        private volatile boolean quit = false;

        void quit(){
            quit = true;
        }

        public synchronized void setTask(Runnable task) {
            this.task = task;
        }

        void loop(){
            synchronized (this){
                while (!quit){
                    if(task != null) {
                        task.run();
                        task = null;
                    }
                }
            }
        }

        //myLooper():
//        public static @Nullable android.os.Looper myLooper() {
//            return sThreadLocal.get();
//        }
    }

}
