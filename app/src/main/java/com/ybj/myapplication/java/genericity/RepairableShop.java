package com.ybj.myapplication.java.genericity;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public interface RepairableShop<T> extends Shop<T>{
    void repair(T item);
}
