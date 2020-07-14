package com.ybj.myapplication.kotlin.recyclerView.tools

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

/**
 * Created by 杨阳洋 on 2020/7/13.
 * 调用顺序onDraw -> getItemOffsets -> onDrawOver
 */
class MyItemDecoration : ItemDecoration() {
    var dividerHeight:Float = 0f
    var paint:Paint  = Paint()

    init {
        with(paint){
            isAntiAlias = true
            color = Color.RED
        }
    }

    /**
     * 绘制在最上层
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }

    /**
     * c：通过getItemOffsets撑开的空白区域所对应的画布
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        for (count in 0 until parent.childCount){
            val view = parent.getChildAt(count)
            val index = parent.getChildAdapterPosition(view)
            if (index == 0){
                continue
            }
            c.drawRect(parent.paddingLeft.toFloat(),view.top - dividerHeight,
                (parent.width - parent.paddingRight).toFloat(), view.top.toFloat(),paint)
        }
    }

    /**
     * 给Item的四周加上边距
     * outRect:给item四周加上边距
     * view：当前item对象
     * parent：RecyclerView本身
     * state：通过State可以获取当前RecyclerView状态
     */
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != 0){
            outRect.top = 1
            dividerHeight = 1f
        }
    }
}