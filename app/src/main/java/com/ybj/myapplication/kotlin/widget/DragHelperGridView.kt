package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper


/**
 * Created by 杨阳洋 on 2020/7/30.
 * DragHelper练习
 */
class DragHelperGridView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {

    private val COLUMNS = 2
    private val ROWS = 3
    var dragHelper:ViewDragHelper

    init {
        dragHelper = ViewDragHelper.create(this,DragCallback())
    }

    /*测量绘制过程*/
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val widthSpec = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpec = MeasureSpec.getSize(heightMeasureSpec)
        val childWidth = widthSpec / COLUMNS
        val childHeight = heightSpec / ROWS

        measureChildren(childWidth,childHeight)
        setMeasuredDimension(widthSpec,heightSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        for (index in 0 until childCount){
            val childView = getChildAt(index)
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
            childView.layout(childLeft,childTop,childLeft + childWidth,childTop + childHeight)
        }
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        dragHelper.processTouchEvent(event)
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) ViewCompat.postInvalidateOnAnimation(this)
    }

    /*拖拽过程*/
    inner class DragCallback : ViewDragHelper.Callback() {

        var capturedLeft = 0f
        var capturedTop = 0f

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return true
        }

        override fun onViewDragStateChanged(state: Int) {
            if (state == ViewDragHelper.STATE_IDLE){
                val view = dragHelper.capturedView
                if (view != null) view.elevation = (view.elevation -1)
            }
        }
        /*设置左边最大距离*/
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            return left
        }

        /*设置上边最大边界*/
        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        /*View被拖拽起时调用*/
        override fun onViewCaptured(capturedChild: View, activePointerId: Int) {
            capturedChild.elevation = (elevation + 1)
            capturedLeft = capturedChild.left.toFloat()
            capturedTop = capturedChild.top.toFloat()
        }

        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {
            super.onViewPositionChanged(changedView, left, top, dx, dy)
        }

        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            dragHelper.settleCapturedViewAt(capturedLeft.toInt(), capturedTop.toInt())
            postInvalidateOnAnimation()
        }
    }

}