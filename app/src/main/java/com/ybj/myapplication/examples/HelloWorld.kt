package com.ybj.myapplication.examples



/**
 * Created by 杨阳洋 on 2020/6/20.
 */
fun main(){
    println("你好世界")

    val functions = Functions()
    functions.printMessageWithPrefix("你好世界")
    functions.printMessageWithPrefix("你好世界","Erki")
    functions.printMessageWithPrefix(message = "你好世界",prefix = "Uart")

    infix fun Int.times(str:String) = str.repeat(this)
    println(2 times "Bye")
    val pair = "Ferrari" to "Katrina"
    println(pair)
    infix fun String.onto(other:String) = Pair<String,String>(this,other)
    val myPair = "McLaren" onto "Lucas"
    println(myPair)
    println("erki" onto "Mc")
    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia
    println(sophia.likedPeople)

    operator fun String.get(range: IntRange) = substring(range)  // 3
    val str = "Always forgive your enemies; nothing annoys them so much."
    println(str[0..14])

    fun printlnAll(vararg message:String){
        for (m in message) println(m)
    }

    printlnAll("1","2","3","4","5")

}

class Person(val name:String){
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other:Person){likedPeople.add(other)}
}
