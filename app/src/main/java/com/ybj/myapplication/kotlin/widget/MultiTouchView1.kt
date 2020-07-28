package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.MotionEvent.*
import android.view.View
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/28.
 * 多点触控练习(接力型)
 */
class MultiTouchView1(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

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

    var trackingPointerId:Int = 0

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
        when(event.actionMasked){
            ACTION_MOVE ->{
                val pointerIndex = event.findPointerIndex(trackingPointerId)
                offsetX = event.getX(pointerIndex) - downX + originX
                offsetY = event.getY(pointerIndex) - downY + originY
                invalidate()
            }
            ACTION_DOWN ->{
                trackingPointerId = event.getPointerId(0)
                downX = event.x
                downY = event.y
                originX = offsetX
                originY = offsetY
            }
            ACTION_POINTER_DOWN ->{//第二根手指按下
                val actionIndex = event.actionIndex
                trackingPointerId = event.getPointerId(actionIndex)
                downX = event.getX(actionIndex)
                downY = event.getY(actionIndex)
                originY = offsetY
                originX = offsetX
            }
            ACTION_POINTER_UP ->{
                //获取序号
                val actionIndex = event.actionIndex
                //获取序号id
                val pointerId = event.getPointerId(actionIndex)
                if (pointerId == trackingPointerId){
                    var newIndex = if (actionIndex == event.pointerCount - 1){//pointerCount：从1开始
                        event.pointerCount - 2
                    }else{
                        event.pointerCount - 1
                    }
                    trackingPointerId = event.getPointerId(newIndex)
                    downX = event.getX(newIndex)
                    downY = event.getY(newIndex)
                    originY = offsetY
                    originX = offsetX
                }
            }
        }
        return true
    }

}