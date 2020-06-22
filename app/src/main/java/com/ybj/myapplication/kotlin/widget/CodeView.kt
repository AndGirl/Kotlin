package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp
import java.util.*


/**
 * Created by 杨阳洋 on 2020/6/21.
 */
class CodeView(context:Context,attr:AttributeSet ?= null) :TextView(context,attr){
    val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        color = context.getColor(R.color.colorAccent)
        strokeWidth = 6f.dp
    }

    var codeList = arrayOf("android","ios")

    init {
        setTextSize(TypedValue.COMPLEX_UNIT_SP,18f)
        gravity = Gravity.CENTER
        setBackgroundColor(context.getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)
    }

    fun updateCode(){
        val random = Random().nextInt(codeList.size)
        text = codeList.get(random)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
    }

}