package com.ybj.myapplication.kotlin.base

import android.app.Application
import android.content.Context

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
class BaseApplication:Application() {

    companion object{
        lateinit var currentApplication:Context
            private set
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }
}