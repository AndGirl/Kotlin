package com.ybj.myapplication.java.genericity;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public interface InterfaceGenericity<T> {
    //Tiger: InterfaceGenericity<Meat> Sheep:InterfaceGenericity<Plant>
    void eat(T t);

}
