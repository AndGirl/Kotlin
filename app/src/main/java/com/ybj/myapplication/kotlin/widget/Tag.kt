package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.ybj.myapplication.kotlin.utils.dp
import java.util.*


/**
 * Created by 杨阳洋 on 2020/7/26.
 */
class Tag(context: Context?, attrs: AttributeSet?) : AppCompatTextView(context, attrs) {
    private val COLORS = intArrayOf(
        Color.parseColor("#E91E63"),
        Color.parseColor("#673AB7"),
        Color.parseColor("#3F51B5"),
        Color.parseColor("#2196F3"),
        Color.parseColor("#009688"),
        Color.parseColor("#FF9800"),
        Color.parseColor("#FF5722"),
        Color.parseColor("#795548")
    )
    private val TEXT_SIZES = intArrayOf(
        16, 22, 28
    )

    private val random: Random = Random()
    private val CORNER_RADIUS = 4f.dp
    private val X_PADDING = 16f.dp
    private val Y_PADDING = 8f.dp

    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        setTextColor(Color.WHITE)
        textSize = TEXT_SIZES[random.nextInt(3)].toFloat()
        paint.color = COLORS[random.nextInt(COLORS.size)]
        setPadding(X_PADDING.toInt(), Y_PADDING.toInt(), X_PADDING.toInt(), Y_PADDING.toInt())
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawRoundRect(0F, 0F, width.toFloat(),
            height.toFloat(),CORNER_RADIUS,CORNER_RADIUS,paint)
        super.onDraw(canvas)
    }

}