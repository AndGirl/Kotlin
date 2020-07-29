package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import android.view.ViewGroup
import android.widget.OverScroller


/**
 * Created by 杨阳洋 on 2020/7/29.
 */
class TwoViewPager(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    /*记录手指按下位置*/
    var downX = 0f
    var downY = 0f
    /*记录并返回当前View左边界的值*/
    var downScrollX = 0f
    /*记录当前View是否在滑动状态*/
    var isScrolling = false
    var viewConfiguration:ViewConfiguration
    /*用于自动计算滑动的距离*/
    var overScroll: OverScroller
    /*手指移动速度*/
    var velocityTracker:VelocityTracker
    /*滑动速度的最值*/
    var minVelocity = 0f
    var maxVelocity = 0f
    init {
        viewConfiguration = ViewConfiguration.get(context)
        overScroll = OverScroller(context)
        velocityTracker = VelocityTracker.obtain()
        maxVelocity = viewConfiguration.scaledMaximumFlingVelocity.toFloat()
        minVelocity = viewConfiguration.scaledMinimumFlingVelocity.toFloat()

    }

    /*测量布局流程*/
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        /*给所有的子View设置统一的限制*/
        measureChildren(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        var childLeft = 0
        var childTop = 0
        var childRight = width
        var childBottom = height
        for (count in 0 until  childCount){
            var childView = getChildAt(count)
            childView.layout(childLeft,childTop,childRight,childBottom)
            childLeft += width
            childRight += width
        }
    }

    /*触摸事件*/
    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (ev.actionMasked == MotionEvent.ACTION_DOWN){
            velocityTracker.clear()
        }
        velocityTracker.addMovement(ev)
        var result = false
        when(ev.actionMasked){
            MotionEvent.ACTION_DOWN ->{
                isScrolling = false
                downX = ev.x
                downY = ev.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE ->{
                if (!isScrolling){
                    var dx = downX - ev.x
                    if (Math.abs(dx) > viewConfiguration.scaledPagingTouchSlop){
                        isScrolling = true
                        parent.requestDisallowInterceptTouchEvent(true)
                        result = true
                    }
                }
            }
        }
        return result
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {

        if (event.actionMasked == MotionEvent.ACTION_DOWN){
            velocityTracker.clear()
        }
        velocityTracker.addMovement(event)
        when(event.actionMasked){
            MotionEvent.ACTION_DOWN ->{
                downX = event.x
                downY = event.y
                downScrollX = scrollX.toFloat()
            }
            MotionEvent.ACTION_MOVE ->{
                var dx = downX - event.x + downScrollX
                if (dx > width * (childCount - 1)){
                    dx = width * (childCount - 1).toFloat()
                }else if(dx < 0){
                    dx = 0f
                }
                Log.e("dx",dx.toString())
                scrollTo(dx.toInt(),0)
            }
            MotionEvent.ACTION_UP ->{
                velocityTracker.computeCurrentVelocity(1000,maxVelocity)
                var vx = velocityTracker.xVelocity
                Log.e("vx",vx.toString())
                var upScrollX = scrollX
                Log.e("upScrollX",upScrollX.toString())
                var targetPage: Int
                var curretPage = (downScrollX/width).toInt()
                targetPage = if (vx > 0){//右滑
                    if ((event.x - downX) > width /4){
                        if (curretPage == 0){
                            curretPage
                        }else{
                            curretPage - 1
                        }
                    }else{
                        curretPage
                    }

                }else if (vx == 0f){
                    curretPage
                }else{//左滑
                    if ((downX - event.x) > width / 4){
                        if (curretPage == childCount - 1){
                            curretPage
                        }else{
                            curretPage + 1
                        }
                    }else{
                        curretPage
                    }

                }

                val scrollDistance = width * targetPage - upScrollX
                overScroll.startScroll(scrollX,0, scrollDistance,0)
                postInvalidateOnAnimation()
            }
        }
        return true
    }

    override fun computeScroll() {
        if (overScroll.computeScrollOffset()){
            scrollTo(overScroll.currX,overScroll.currY)
            postInvalidateOnAnimation()
        }
    }

}