package com.ybj.myapplication.kotlin.arithemtic

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 查找数组中最大元素
 */
class Lesson1_3 {
    fun test() {
        val a = intArrayOf(1, 2, 3, 4, 5, 4, 3, 2, 1, 2)
        var timeMax = 0
        var valMax = -1
        var timeTmp = 0
        for (i in a.indices) {
            timeTmp = 0
            for (j in a.indices) {
                if (a[i] == a[j]) {
                    timeTmp += 1
                }
            }
            if (timeTmp > timeMax) {
                timeMax = timeTmp
                valMax = a[i]
            }
        }
        println(valMax)
    }
}
