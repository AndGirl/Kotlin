package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/21.
 */
class CircleView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var radius = 50f.dp
        set(value) {
            field = value
            invalidate()
        }
    var padding = 15f.dp

    init {
        paint.color = Color.RED
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(width/2f,height/2f,radius,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            ACTION_DOWN ->{

            }
            ACTION_MOVE ->{

            }
            ACTION_CANCEL -> {

            }
            ACTION_UP ->{
                performClick()
            }
        }
        return true
    }

}