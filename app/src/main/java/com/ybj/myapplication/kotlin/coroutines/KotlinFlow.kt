package com.ybj.myapplication.kotlin.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking

/**
 * Created by 杨阳洋 on 2020/6/22.
 */
fun foo():List<Int> = listOf(1,2,3)

fun fooo():Sequence<Int> = sequence {
    for (i in 1..3){
        Thread.sleep(100)
        yield(i)
    }
}

fun flow():kotlinx.coroutines.flow.Flow<Int> = kotlinx.coroutines.flow.flow(){
    for (i in 1..3){
        delay(100L)
        emit(i)
    }
}


suspend fun performRequest(request:Int):String{
    delay(1000)
    return "response $request"
}

fun main(){

    foo().forEach { value -> println(value) }

    runBlocking {
        (1..3).asFlow()
            .transform {
                    request -> emit("Making request $request")
                emit(performRequest(request))
            }
            .collect { response -> println(response) }
    }

}