package com.ybj.myapplication.kotlin.utils

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
interface LogNode {
    fun println(priority:Int,tag:String?,msg:String?,tr:Throwable?)
}