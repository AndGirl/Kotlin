package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.ArithmeticTest;

/**
 * Created by 杨阳洋 on 2020/7/7.
 */
public class Lesson1_3 implements ArithmeticTest {


    public static void main(String [] args){
        Lesson1_3 lesson1_1 = new Lesson1_3();
        lesson1_1.test();
    }

    @Override
    public void test() {
        int a[] = {1,2,3,4,5,4,3,2,1,2};
        int time_max = 0;
        int val_max = -1;
        int time_tmp = 0;
        for (int i = 0 ; i < a.length ; i++){
            time_tmp = 0;
            for (int j = 0 ; j < a.length ; j++){
                if(a[i] == a[j]) {
                    time_tmp += 1;
                }
            }
            if(time_tmp > time_max) {
                time_max = time_tmp;
                val_max = a[i];
            }

        }
        System.out.println(val_max);
    }

}
