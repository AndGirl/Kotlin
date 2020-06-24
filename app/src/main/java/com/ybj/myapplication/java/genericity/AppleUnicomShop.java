package com.ybj.myapplication.java.genericity;

import java.io.Serializable;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public class AppleUnicomShop implements SimShop<Apple,ChinaUnicom> {

    @Override
    public ChinaUnicom getSim(String name, String id) {
        return null;
    }

    @Override
    public Apple buy() {
        return null;
    }

    @Override
    public float refund(Apple item) {
        return 0;
    }

    @Override
    public <E> E refurbish(E item, float money) {
        return null;
    }

    @Override
    public <E extends Runnable & Serializable> void someMethod(E param) {

    }

}
