package com.ybj.myapplication.kotlin.http

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
interface EntityCallback<T> {

    fun onSuccess(entity:T)

    fun onFailure(message:String)

}