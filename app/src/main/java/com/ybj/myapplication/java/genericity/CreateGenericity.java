package com.ybj.myapplication.java.genericity;

import java.util.Arrays;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public class CreateGenericity<T> {
    private T instance;

    public T getInstance() {
        return instance;
    }

    public void setInstance(T instance) {
        this.instance = instance;
    }

    private Object[] list = new Object[0];

    public T getList(int index) {
        return (T)list[index];
    }

    public void setList(T newItem) {
        list  = Arrays.copyOf(list,list.length + 1);
        list[list.length - 1] = newItem;
    }
}
