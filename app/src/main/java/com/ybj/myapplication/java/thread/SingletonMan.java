package com.ybj.myapplication.java.thread;

/**
 * Created by 杨阳洋 on 2020/6/24.
 */
public class SingletonMan {
    private static volatile SingletonMan ourInstance;
    public static SingletonMan getInstance() {
        if(ourInstance == null) {
            synchronized (SingletonMan.class){
                if(ourInstance == null) {
                    return ourInstance = new SingletonMan();
                }
            }
        }
        return ourInstance;
    }

    private SingletonMan() {
    }
}
