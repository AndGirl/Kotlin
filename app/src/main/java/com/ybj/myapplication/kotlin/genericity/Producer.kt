package com.ybj.myapplication.kotlin.genericity

import android.widget.Button
import android.widget.TextView

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
class Producer<T>{
    fun producer(): T? {
        return null
    }

}

fun main(){
    val producer:Producer<out TextView> = Producer<Button>()
    val textView: TextView? = producer.producer()
}