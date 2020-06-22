package com.ybj.myapplication.examples

/**
 * Created by 杨阳洋 on 2020/6/20.
 */
class Functions {

    fun printMessage(message:String):Unit{
        println(message)
    }

    fun printMessageWithPrefix(message:String,prefix:String = "info"){
        println("[$prefix]$message")
    }

    fun sum(x:Int,y:Int):Int{
        return x + y
    }

    fun multiply(x : Int,y:Int) = x * y

}