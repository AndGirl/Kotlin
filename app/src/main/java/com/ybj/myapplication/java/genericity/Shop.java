package com.ybj.myapplication.java.genericity;

import java.io.Serializable;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public interface Shop<T> {
    T buy();
    float refund(T item);

    <E>E refurbish(E item,float money);

    <E extends Runnable & Serializable> void someMethod(E param);

}
