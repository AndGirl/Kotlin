package com.ybj.myapplication.kotlin.utils

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
class MessageOnlyLogFilter :LogNode{

    var next:LogNode ?= null

    override fun println(priority: Int, tag: String?, msg: String?, tr: Throwable?) {
        next?.println(Log.NONE,null,msg,null)
    }

}