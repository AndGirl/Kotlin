package com.ybj.myapplication.java.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by 杨阳洋 on 2020/6/24.
 */
public class ReadWriteLockDemo implements TestDemo{

    ReentrantLock lock = new ReentrantLock();
    private int x = 0;

    private void count(){
        lock.lock();
        x++;
        lock.unlock();
    }

    private void println(int time){
        System.out.println(time);
    }


    @Override
    public void runTest() {

    }
}
