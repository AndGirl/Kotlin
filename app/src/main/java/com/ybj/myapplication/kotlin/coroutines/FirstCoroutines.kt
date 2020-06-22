package com.ybj.myapplication.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by 杨阳洋 on 2020/6/22.
 */
class FirstCoroutines{

}

fun main() = runBlocking{
    val job = launch {
        repeat(1000){
            i -> println("job : I'm sleeping $i")
            delay(500L)
        }
    }
    delay(10000L)
    println("main: I'm tired of waiting!")//协程在等待时，主线程还在继续
    job.cancel()//取消该任务
    job.join()//等待该任务结束
    println("main: Now I can quit")
}