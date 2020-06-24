package com.ybj.myapplication.java;

import com.ybj.myapplication.java.thread.Synchronized1Demo;
import com.ybj.myapplication.java.thread.Synchronized2Demo;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by 杨阳洋 on 2020/6/24.
 */
public class TestThread {

    public static void main(String [] args){
        runSynchronized2Demo();
    }

    /**
     * 使用Thread类来定义工作
     */
    static void thread(){
        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                System.out.println("Thread started!");
            }
        };
        thread.start();
    }

    static void runnable(){
        /**
         * 使用Runnable来定义工作
         */
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with Runnable started!");
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    static void threadFactory(){
        ThreadFactory threadFactory = new ThreadFactory() {
            AtomicInteger count = new AtomicInteger(0);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r,"Thread-" + count.incrementAndGet());
            }
        };
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " started!" );
            }
        };

        Thread thread = threadFactory.newThread(runnable);
        thread.start();
        Thread newThread = threadFactory.newThread(runnable);
        newThread.start();

    }

    static void executor(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread with Runnable started!");
            }
        };

        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(runnable);
        cachedThreadPool.execute(runnable);
        cachedThreadPool.execute(runnable);
        cachedThreadPool.shutdown();

        BlockingDeque<Runnable> queue = new LinkedBlockingDeque<>(10000);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 100, 5, TimeUnit.SECONDS, queue);

    }

    public void callable(){
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(1000);
                return "Done!";
            }
        };

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<String> submit = executorService.submit(callable);
        try {
            while(!submit.isDone()) {
                //做点事情
            }
            String result = submit.get();
            System.out.println("result : " + result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void runSynchronized1Demo(){
        new Synchronized1Demo().runTest();
    }

    public static void runSynchronized2Demo(){
        new Synchronized2Demo().runTest();
    }

}
