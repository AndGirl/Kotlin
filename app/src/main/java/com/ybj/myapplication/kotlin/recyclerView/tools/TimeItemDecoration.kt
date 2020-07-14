package com.ybj.myapplication.kotlin.recyclerView.tools

import android.graphics.*
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.base.BaseApplication
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/13.
 */
class TimeItemDecoration : RecyclerView.ItemDecoration(){

    var paint = Paint()
    val leftOffset = 40f.dp
    var topOffset = 20f.dp
    val radius = 8f.dp

    init {
        with(paint){
            isAntiAlias = true
            color = Color.RED
        }
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        for (count in 0 .. parent.childCount){
            val view = parent.getChildAt(count)
            val position = parent.getChildAdapterPosition(view)
            if (position > 3){
                c.drawBitmap(BitmapFactory.decodeResource(BaseApplication.currentApplication.resources, R.drawable.earth),
                    leftOffset,view.top.toFloat(),paint)
            }
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        for (count in 0 until parent.childCount){
            val view = parent.getChildAt(count)
            val index = parent.getChildAdapterPosition(view)
            var dividerTop = view.top - topOffset
            if (index == 0){
                dividerTop = view.top.toFloat()
            }
            val dividerLeft = parent.paddingLeft
            val dividerBottom = view.bottom
            val dividerRight = parent.width - parent.paddingRight
            val centerX = dividerLeft + leftOffset / 2
            val centerY = dividerTop +  (dividerBottom - dividerTop) / 2

            val upTopX = centerX
            val upTopY = dividerTop
            val upBottomX = centerX
            val upBottomY = centerY - radius

            c.drawLine(upTopX,upTopY,upBottomX,upBottomY,paint)

            paint.style = Paint.Style.STROKE
            c.drawCircle(centerX,centerY,radius,paint)
            paint.style = Paint.Style.FILL_AND_STROKE

            val downLineTopX = centerX
            val downLineTopY = centerY + radius
            val downLineBottomX = centerX
            val downLineBottomY = dividerBottom.toFloat()

            c.drawLine(downLineTopX,downLineTopY,downLineBottomX,downLineBottomY,paint)

            c.drawLine(leftOffset, dividerBottom.toFloat(),
                (view.right-parent.paddingRight).toFloat(), dividerBottom.toFloat(),paint)
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        if (parent.getChildAdapterPosition(view) != 0){
            outRect.top = 1
            topOffset = 1f
        }
        outRect.left = leftOffset.toInt()
    }
}