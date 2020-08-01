package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.FrameLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.ybj.myapplication.R


/**
 * Created by 杨阳洋 on 2020/7/30.
 */
class DragUpDownLayout(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {
    var view: View? = null
    lateinit var dragHelper: ViewDragHelper
    var dragListener: ViewDragHelper.Callback = DragCallback()
    lateinit var viewConfiguration: ViewConfiguration

    init {
        dragHelper = ViewDragHelper.create(this, dragListener)
        viewConfiguration = ViewConfiguration.get(context)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        view = findViewById(R.id.view)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        return dragHelper.shouldInterceptTouchEvent(ev!!)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        dragHelper.processTouchEvent(event!!)
        return true
    }

    override fun computeScroll() {
        if (dragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(this)
        }
    }

    inner class DragCallback : ViewDragHelper.Callback() {
        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            return child === view
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            return top
        }

        override fun onViewReleased(
            releasedChild: View, xvel: Float,
            yvel: Float
        ) {
            if (Math.abs(yvel) > viewConfiguration.scaledMinimumFlingVelocity) {
                if (yvel > 0) {
                    dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
                } else {
                    dragHelper.settleCapturedViewAt(0, 0)
                }
            } else {
                if (releasedChild.top < height - releasedChild.bottom) {
                    dragHelper.settleCapturedViewAt(0, 0)
                } else {
                    dragHelper.settleCapturedViewAt(0, height - releasedChild.height)
                }
            }
            postInvalidateOnAnimation()
        }
    }
}