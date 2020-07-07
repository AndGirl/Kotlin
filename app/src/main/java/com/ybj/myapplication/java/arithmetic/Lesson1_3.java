package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.ArithmeticTest;

import java.util.Arrays;

/**
 * Created by 杨阳洋 on 2020/7/7.
 */
public class Lesson1_1 implements ArithmeticTest {


    public static void main(String [] args){
        Lesson1_1 lesson1_1 = new Lesson1_1();
        lesson1_1.test();
    }

    @Override
    public void test() {
        int a[] = {1,2,3,4,5};
        int b[] = new int[5];
        for (int i = 0; i < a.length ; i++){
            b[a.length - 1 - i] = a[i];
        }
        //时间复杂度：o(n) 空间复杂度:o(n)
        System.out.println(Arrays.toString(b));

        int temp = 0;
        for (int i = 0 ; i < a.length/2 ; i++){
            temp = a[i];
            a[i] = a[a.length - 1 - i];
            a[a.length - 1 - i] = temp;
        }
        //时间复杂度：o(n) 空间复杂度:o(1)
        System.out.println("a----"+Arrays.toString(a));
    }

}
