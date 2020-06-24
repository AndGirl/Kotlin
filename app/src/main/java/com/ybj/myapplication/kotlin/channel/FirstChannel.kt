package com.ybj.myapplication.kotlin.channel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created by 杨阳洋 on 2020/6/22.
 */

fun main() = runBlocking {
    val channel = Channel<Int>()
    launch {
        for (x in 1..5) channel.send(x * x)
        channel.close()//我们结束发送
    }
    //这里我们使用for循环来打印所有被接收到的元素（直到通道关闭）
    for (y in channel) println(y)
    println("Done！")

    val squares = produceSquares()
    squares.consumeEach { println(it) }
    println("Done!")

}

fun CoroutineScope.produceSquares():ReceiveChannel<Int> = produce {
    for(x in 1..5) send(x * x)
}
