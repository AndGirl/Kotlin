package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.ArithmeticTest;

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 任意多张面额2元、3元、7元的货币，要凑出100元共有多少种方案
 */
public class Lesson2_1 implements ArithmeticTest {


    public static void main(String[] args) {
        Lesson2_1 lesson1_1 = new Lesson2_1();
        lesson1_1.test();
    }

    @Override
    public void test() {
        int count = 0;
        for (int i = 0; i <= (100 / 7); i++) {
            for (int j = 0; j <= (100 / 3); j++) {
                if ((100 - 7*i - 3*j) %2 == 0){
                    count+=1;
                }
            }
        }
        System.out.println(count);
    }

}
