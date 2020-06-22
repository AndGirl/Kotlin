package com.ybj.myapplication.kotlin.utils

import android.content.res.Resources
import android.util.TypedValue
import android.widget.Toast
import com.ybj.myapplication.kotlin.base.BaseApplication

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
val displayMetrics = Resources.getSystem().displayMetrics
val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, displayMetrics)

fun toast(string: String, duration: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(BaseApplication.currentApplication, string, duration).show()
