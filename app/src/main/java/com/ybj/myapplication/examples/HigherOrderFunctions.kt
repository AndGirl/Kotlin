package com.ybj.myapplication.examples

/**
 * Created by 杨阳洋 on 2020/6/20.
 */
class HigherOrderFunctions {

    fun calculate(x:Int,y:Int,operation:(Int,Int) -> Int):Int{
        return operation(x,y)
    }

    fun sum(x:Int,y:Int) = x + y

    fun square(x:Int) = x * x

    fun operation():(Int) -> Int{
        return ::square
    }

    val upperCase1: (String) -> String = { str: String -> str.toUpperCase() }
    //内部类型推断：lambda参数的类型从为其分配的变量的类型推断。
    val upperCase2: (String) -> String = { str -> str.toUpperCase() }
    //在lambda之外进行类型推断
    val upperCase3 = { str: String -> str.toUpperCase() }                     // 3
    //对于具有单个参数的lambda，你不必显示命名它。相反，你可以使用隐式it变量。
    //当It可以推断出的类型（通常是这种情况）时，这特别有用。
    val upperCase5: (String) -> String = { it.toUpperCase() }
    //如果lambda由一个函数调用组成，则可以使用函数指针（::）
    val upperCase6: (String) -> String = String::toUpperCase

    val numbers = listOf<Int>(1,-2,3,-4,5,-6)

    val positives = numbers.filter { x -> x > 0 }
    val negatives = numbers.filter { it < 0 }

}

fun Order.maxPricedItemValue():Float = this.items.maxBy { it.price }?.price ?: 0F

fun Order.maxPricedItemName() = this.items.maxBy { it.price }?.name ?: "NO_PRODUCTS"

val Order.commaDelimitedItemNames:String
    get() = items.map { it.name }.joinToString()

fun main(){
    val higherOrderFunctions = HigherOrderFunctions()
    val sumResult = higherOrderFunctions.calculate(4,5,higherOrderFunctions::sum)
    val mulResult = higherOrderFunctions.calculate(4,5){a,b -> 4* 5}
    println("sumResult = $sumResult,mulResult = $mulResult")

    val operation = higherOrderFunctions.operation()
    println(operation(2))

    val order = Order(listOf(Item("Bread", 25.0F), Item("Wine", 29.0F), Item("Water", 12.0F)))
    println("Max priced item name: ${order.maxPricedItemValue()}")                     // 4
    println("Max priced item value: ${order.maxPricedItemName()}")
    println("Items: ${order.commaDelimitedItemNames}")

}