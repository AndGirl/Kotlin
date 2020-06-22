package com.ybj.myapplication.kotlin.utils

import android.content.Context
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.base.BaseApplication

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
object CacheUtils {
    val context = BaseApplication.currentApplication
    val SP = context.getSharedPreferences(R.string.app_name.toString(), Context.MODE_PRIVATE)
    fun save(key:String,value:String){
        SP.edit().putString(key,value).apply()
    }
    fun get(key:String) = SP.getString(key,null)

}