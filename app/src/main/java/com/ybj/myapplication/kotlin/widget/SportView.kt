package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/20.
 */
class SportView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /*圆半径*/
    private val radius = 150f.dp
    private var strokeWidth = 15f.dp
    private var centerX:Float = 0f
    private var centerY:Float = 0f
    val text = "center"
    val left = "left"
    val rightBottom = "rightBottom"
    var rect = Rect()
    var offset = 0
    var fontMetrics = Paint.FontMetrics()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = width / 2f
        centerY = height / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制圆环
        paint.style = Paint.Style.STROKE
        paint.color = Color.DKGRAY
        paint.strokeWidth = strokeWidth
        canvas.drawCircle(centerX,centerY,
            radius,paint)

        //绘制进度条
        paint.color = Color.RED
        paint.strokeCap = Paint.Cap.ROUND
        canvas.drawArc(centerX - radius,centerY - radius,centerX + radius,centerY + radius,
            90f,240f,false,paint)

        canvas.drawCircle(centerX,centerY,2f.dp,paint)

        //绘制文本
        paint.color = Color.GREEN
        paint.style = Paint.Style.FILL
        paint.textSize = 50f.dp
        //静态文字
        paint.getTextBounds(text,0,text.length,rect)
        offset = (rect.top + rect.bottom) / 2
        paint.textAlign = Paint.Align.CENTER
        canvas.drawText(text,centerX,centerY - offset,paint)

        //动态文字
        paint.getFontMetrics(fontMetrics)
        offset = ((fontMetrics.descent + fontMetrics.ascent) /2).toInt()
        canvas.drawText(text,centerX,centerY - offset,paint)

        //左对齐
        paint.textAlign = Paint.Align.LEFT
        paint.getTextBounds(left,0,left.length,rect)
        canvas.drawText(left, -rect.left.toFloat(), -rect.top.toFloat(),paint)
        paint.getTextBounds(rightBottom,0,rightBottom.length,rect)
        //右下对齐
        paint.textAlign = Paint.Align.LEFT
        canvas.drawText(rightBottom,
            (width - rect.right).toFloat(), height-rect.bottom.toFloat(),paint)

    }

}