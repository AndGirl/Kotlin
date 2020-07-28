package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/28.
 * 多点触控：独立型
 */
class MultiTouchView3(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
        .apply {
            this.style = Paint.Style.STROKE
            this.strokeWidth = 4f.dp
            this.strokeCap = Paint.Cap.ROUND
            this.strokeJoin = Paint.Join.ROUND
        }
    var paths = SparseArray<Path>()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in 0 until paths.size()){
            val path = paths.valueAt(i)
            canvas.drawPath(path,paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN,MotionEvent.ACTION_POINTER_DOWN ->{
                var actionIndex = event.actionIndex
                var pointerId = event.getPointerId(actionIndex)
                var path = Path()
                path.moveTo(event.getX(actionIndex),event.getY(actionIndex))
                paths.append(pointerId,path)
                invalidate()
            }
            MotionEvent.ACTION_MOVE ->{
                for (i in 0 until event.pointerCount){
                    var pointerId = event.getPointerId(i)
                    var path = paths[pointerId]
                    path.lineTo(event.getX(i),event.getY(i))
                }
                invalidate()
            }
            MotionEvent.ACTION_POINTER_UP,MotionEvent.ACTION_UP->{
                var actionIndex = event.actionIndex
                var pointerId = event.getPointerId(actionIndex)
                paths.remove(pointerId)
                invalidate()
            }
        }
        return true
    }

}