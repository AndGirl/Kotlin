package com.ybj.myapplication.examples

/**
 * Created by 杨阳洋 on 2020/6/20.
 */
data class User (val name:String,val id:Int){

    fun rentPrice(standardDays:Int,festivityDays:Int,specialDays:Int):Unit{
        val dayRates = object{
            var standard:Int = 30 * standardDays
            var festivity:Int = 50 * festivityDays
            var special :Int = 100 * specialDays
        }

        var total = dayRates.standard + dayRates.festivity + dayRates.special
        println("Total price : $$total")
    }

    companion object ChinaUser{
        fun getChinaUserNumber(number : Int){
            println(number)
        }
    }

}

fun main(){

    User.ChinaUser.getChinaUserNumber(50)

    val user = User("Alex",1)
    val secondUser = User("Alex",1)
    val thirdUser = User("Alex",2)

    println("user == secondUser: ${user == secondUser}")
    println("user == thirdUser: ${user == thirdUser}")

    println(user.hashCode())
    println(thirdUser.hashCode())

    println(user.copy())
    println(user.copy("Max"))

    println("name = ${user.component1()}")
    println("id = ${user.component2()}")
}