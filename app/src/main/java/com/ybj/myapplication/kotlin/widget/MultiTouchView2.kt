package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/28.
 * 多点触控：协作型
 */
class MultiTouchView2(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var bitmap: Bitmap
    val IMAGE_WIDTH = 300f.dp
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    /*偏移量*/
    var offsetX = 0f
    var offsetY = 0f
    /*按下的点*/
    var downX = 0f
    var downY = 0f

    var originX = 0f
    var originY = 0f
    init {
        bitmap = getAvatar(IMAGE_WIDTH.toInt())
    }

    fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avtor, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avtor, options)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap,offsetX,offsetY,paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        Log.e("index",event.actionIndex.toString())
        var sumX = 0f
        var sumY = 0f
        var pointerUp = (event.actionMasked == MotionEvent.ACTION_POINTER_UP)
        for (i in 0 until event.pointerCount){
            if (!pointerUp || i != event.actionIndex){//不是抬起事件并且不是抬起的那根手指
                sumX += event.getX(i)
                sumY += event.getY(i)
            }
        }
        var pointerCount = event.pointerCount
        if (pointerUp){
            pointerCount--
        }
        var focusX = sumX / pointerCount
        var focusY = sumY / pointerCount
        when(event.actionMasked){
            MotionEvent.ACTION_MOVE ->{
                offsetX = focusX - downX + originX
                offsetY = focusY - downY + originY
                invalidate()
            }
            MotionEvent.ACTION_DOWN ,MotionEvent.ACTION_POINTER_DOWN ,MotionEvent.ACTION_POINTER_UP ->{
                downX = focusX
                downY = focusY
                originX = offsetX
                originY = offsetY
            }
        }
        return true
    }

}