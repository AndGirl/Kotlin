package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/15.
 */
class DashBoard(context:Context,attr:AttributeSet ?= null) : View(context,attr) {

    /*圆半径*/
    private val radius = 150f.dp
    /*刻度线半径*/
    private val strokewidth = 3f.dp
    /*开口角度*/
    private val openAngle = 120f
    /*指针长度*/
    val pointLength = 100f.dp
    /*初始化画笔*/
    var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        style = Paint.Style.STROKE
        this.strokeWidth = strokewidth
    }
    /*中心坐标点*/
    var centerX = 0f
    var centerY= 0f
    /*圆弧*/
    var circlePath = Path()
    /*刻度线*/
    var effectPath = Path().apply {
        addRect(0f,0f,2f.dp,10f.dp,Path.Direction.CCW)
    }
    /*刻度线*/
    lateinit var dashEffect:PathDashPathEffect
    lateinit var pathMeasure:PathMeasure

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = this.width / 2f
        centerY = this.height / 2f
        circlePath.reset()
        circlePath.addArc(centerX - radius,centerY - radius,centerX + radius,centerY + radius,
            90 + openAngle / 2f,360 - openAngle)
        pathMeasure = PathMeasure(circlePath,false)

        dashEffect =
            PathDashPathEffect(effectPath, (pathMeasure.length  - 2f.dp)/ 20f, 0f, PathDashPathEffect.Style.ROTATE)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /*绘制弧形*/
        canvas.drawPath(circlePath,paint)

        /*绘制刻度*/
        paint.pathEffect = dashEffect
        canvas.drawPath(circlePath,paint)
        paint.pathEffect = null

        /*绘制指针*/
        canvas.drawLine(centerX,centerY,
            (centerX + pointLength * Math.cos(getAngleFromMark(3))).toFloat(),
            (centerY + pointLength * Math.sin(getAngleFromMark(3))).toFloat(),paint)
    }

    /*获取指针的指向*/
    fun getAngleFromMark(mark:Int) = Math.toRadians((90 + openAngle / 2f + (360 - openAngle) / 20f * mark).toDouble())

}