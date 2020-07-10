package com.ybj.myapplication.kotlin.arithemtic

/**
 * Created by 杨阳洋 on 2020/7/7.
 * 数组中出现次数最多的数
 */
class Lesson2_2 {
    fun test(){
        val a : IntArray = intArrayOf(1,2,3,4,5,4,3,2,1,2,2)
        var timeMax = 0
        var valMax = -1
        var hashMap:HashMap<Int,Int> = hashMapOf()
        for (i:Int in a.indices){
            if (hashMap.containsKey(a[i])){
                hashMap.put(a[i], (hashMap[a[i]] ?: 1) + 1)
            }else{
                hashMap.put(a[i],1)
            }
        }

        for (key in hashMap.keys){
            if ((hashMap[a[key]]?: 0) > timeMax){
                timeMax = hashMap[a[key]] ?: 0
                valMax = a[key]
            }
        }

        println(valMax)
    }
}

fun main() {
    Lesson2_2().test()
}