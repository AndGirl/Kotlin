package com.ybj.myapplication.kotlin.arithemtic

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 任意多张面额2元、3元、7元的货币，要凑出100元共有多少种方案
 */
class Lesson2_1 {
    fun test(){
        var count : Int = 0
        for (i:Int in 0..100/7){
            for (j:Int in 0..100/3){
                if ((100 - 7*i - 3 *j) % 2==0){
                    count++
                }
            }
        }
        println(count)
    }
}
