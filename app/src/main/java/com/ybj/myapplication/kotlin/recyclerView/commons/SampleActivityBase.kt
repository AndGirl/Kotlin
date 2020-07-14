package com.ybj.myapplication.kotlin.recyclerView.commons

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.kotlin.utils.Log
import com.ybj.myapplication.kotlin.utils.LogWrapper

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
open class SampleActivityBase : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        initializeLogging()
    }

    open fun initializeLogging() {
        val logWrapper = LogWrapper()
        Log.logNode = logWrapper

        Log.i(TAG, "Ready")
    }

    companion object {
        const val TAG = "SampleActivityBase"
    }

}