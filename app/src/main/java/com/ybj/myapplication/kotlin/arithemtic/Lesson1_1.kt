package com.ybj.myapplication.kotlin.arithemtic

import java.util.*

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 倒序打印
 */
fun test(){
    var a:IntArray = intArrayOf(1,2,3,4,5)
    var b:IntArray = IntArray(5)

    for (i:Int in a.indices){
        b[a.size - 1 - i] = a[i]
    }
    //时间复杂度：o(n) 空间复杂度:o(n)
    println(Arrays.toString(b))

    var temp:Int = 0;
    for (i in 0 until a.size / 2) {
        temp = a[i]
        a[i] = a[a.size - 1 - i]
        a[a.size - 1 - i] = temp
    }
    println("a----${Arrays.toString(b)}")

}

fun main(){
    test()
}