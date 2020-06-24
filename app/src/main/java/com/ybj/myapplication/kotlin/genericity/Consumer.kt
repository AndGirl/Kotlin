package com.ybj.myapplication.kotlin.genericity

import android.widget.Button
import android.widget.TextView
import com.ybj.myapplication.kotlin.base.BaseApplication

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
class Consumer<T> {

    fun consume(t:T){}

}

fun main(){
    val consumer:Consumer<in Button> = Consumer<TextView>()
    consumer.consume(Button(BaseApplication.currentApplication))
}