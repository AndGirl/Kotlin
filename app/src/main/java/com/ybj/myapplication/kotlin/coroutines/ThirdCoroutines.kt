package com.ybj.myapplication.kotlin.coroutines

import kotlinx.coroutines.*

/**
 * Created by 杨阳洋 on 2020/6/22.
 */
fun main() = runBlocking {
    val startTime = System.currentTimeMillis()
    val job = launch (Dispatchers.Default){
        var nextPrintTime = startTime
        var i = 0
        while (isActive){
            if (System.currentTimeMillis() >= nextPrintTime){
                println("job : I'm sleeping ${i++}")
                nextPrintTime += 500L
            }
        }
    }
    delay(1300L)
    println("main : I'm tired of waiting!")
    job.cancelAndJoin()
    println("main : Now I can quit")
}