package com.ybj.myapplication.kotlin.arithemtic

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 查找数组中最大元素
 */
class Lesson1_2 {
    fun test() {
        var a:IntArray = intArrayOf(1,4,3)
        var max :Int = -1
        for (i:Int in a.indices){
            if (a[i] > max) max = a[i]
        }
        println(max)
    }
}
