package com.ybj.myapplication.examples

/**
 * Created by 杨阳洋 on 2020/6/20.
 */
class ControlFlow {

    fun cases(obj:Any){
        when(obj){
            1 -> println("One")
            "Hello" -> println("Greeting")
            is Long -> println("Long")
            !is String -> println("Not a String")
            else -> println("Unkonwn")
        }
    }

}

fun main(){
    val zoo = Zoo(listOf(Animal("zebra"),Animal("lion")))
    for (animal in zoo){
        println("Watch out,it's a ${animal.name}")
    }
}