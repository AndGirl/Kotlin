package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/16.
 */
class PieChart(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    /*扇形图样式*/
    val angles = intArrayOf(60,80,100,120)
    /*扇形图颜色*/
    val colors = intArrayOf(Color.parseColor("#448AFF"), Color.parseColor("#9575CD"),
        Color.parseColor("#FF8F00"), Color.parseColor("#00C853"))
    /*扇形图半径*/
    val radius = 150f.dp
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /*扇形绘制范围*/
    lateinit var bounds:RectF
    /*最开始起始角度*/
    var currentAngle = 0
    val offsetLength = 20f.dp

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        currentAngle = 0
        bounds = RectF(width / 2f - radius, height / 2f - radius,
            width / 2f + radius, height / 2f + radius)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (angle in angles.indices){
            if (angle == 1){//需要偏移的扇形图
                canvas.save()
                canvas.translate(
                    (offsetLength * Math.cos(Math.toRadians((currentAngle + angles[angle] / 2f).toDouble()))).toFloat(),
                    (offsetLength *  Math.sin(Math.toRadians((currentAngle + angles[angle] / 2f).toDouble()))).toFloat()
                )
            }
            paint.color = colors[angle]
            canvas.drawArc(bounds, currentAngle.toFloat(), angles[angle].toFloat(),true,paint)
            currentAngle += angles[angle]
            if (angle == 1){
                canvas.restore()
            }
        }

    }

}