package com.ybj.myapplication.java.genericity;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public interface SimShop<T,C extends Sim> extends Shop<T>{

    C getSim(String name,String id);

    T buy();

}
