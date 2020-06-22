package com.ybj.myapplication.kotlin.coroutines

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * Created by 杨阳洋 on 2020/6/22.
 */
fun main() = runBlocking {
    val job = launch {
        try {
            repeat(1000){
                    i -> println("job : I'm sleeping $i")
                delay(500L)
            }
        }finally {
            withContext(NonCancellable){
                println("job : I'm running finally")
                delay(1000L)
                println("job : And I've just delayed for 1 sec beacuse I'm non-cancellable")
            }
        }
    }

    delay(1300L)
    println("main : I'm tired of waiting")
    job.cancelAndJoin()
    println("main : Now I can quit")

    val timeMillis = measureTimeMillis {
        val one = async { doSomethingUsefulOne() }
        val two = async { doSomethingUsefulTwo() }
        println("The answer is ${one.await() + two.await()}")
    }
    println("Completed in $timeMillis ms")

}

suspend fun concurrentSum():Int = coroutineScope {
    val one = async { doSomethingUsefulOne() }
    val two = async { doSomethingUsefulTwo() }
    one.await() + two.await()
}

suspend fun doSomethingUsefulOne():Int{
    delay(1000L)
    return 13
}

suspend fun doSomethingUsefulTwo():Int{
    delay(1000L)
    return 29
}
