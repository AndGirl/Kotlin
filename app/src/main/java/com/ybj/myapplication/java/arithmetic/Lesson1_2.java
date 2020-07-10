package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.ArithmeticTest;

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 查找数组中最大元素
 */
public class Lesson1_2 implements ArithmeticTest {
    @Override
    public void test() {
        int a [] = {1,4,3};
        int max = -1;
        for (int i = 0 ; i < a.length ; i++){
            if(a[i] > max) {
                max = a[i];
            }
        }
        System.out.println(max);

    }
}
