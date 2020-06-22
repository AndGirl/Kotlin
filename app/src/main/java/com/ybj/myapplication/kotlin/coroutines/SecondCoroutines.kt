package com.ybj.myapplication.kotlin.coroutines

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by 杨阳洋 on 2020/6/22.
 */
fun main() = runBlocking {
    launch {
        println("Task from runBlocking")
    }

    coroutineScope {//创建一个协程作用域
        launch {
            println("Task from nested launch")
        }
        println("Task from coroutine scope")
    }

    println("Coroutine scope is over")

}