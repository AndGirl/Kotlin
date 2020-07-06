package com.ybj.myapplication.kotlin.lambda

import kotlin.math.pow

/**
 * Created by 杨阳洋 on 2020/7/6.
 */
class TestLambda {

    fun a(string: (Int) -> String):String{
        return string.toString()
    }

}
fun main(){

    val lambda = TestLambda()
    lambda.a(fun(param:Int):String{
        return param.toString()
    })

    val d = fun(param:Int):String{
        return param.toString()
    }

    2f.pow(10)

}