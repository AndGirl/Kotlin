package com.ybj.myapplication.kotlin.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.widget.ImageView
import android.widget.OverScroller
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.ViewCompat
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp
import java.math.BigDecimal


/**
 * Created by 杨阳洋 on 2020/7/27.
 */
class ScalableImageView(context: Context?, attrs: AttributeSet?) : ImageView(context, attrs){

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    lateinit var bitmap: Bitmap
    val IMAGE_WIDTH = 300f.dp
    val OVER_FRACTION = 1.5f
    /*中心偏移量*/
    var offsetX = 0f
    var offsetY = 0f
    var originoffsetX = 0f
    var originoffsetY = 0f
    /*最大偏移量*/
    var maxOffsetX = 0f
    var maxOffsetY = 0f
    /*放缩因子*/
    var smallScaleFraction = 0f
    var bigScaleFraction = 0f
    var currentFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    /*当前放缩状态*/
    var isBigFraction = false
    /*手势监听器*/
    var gestureDetector: GestureDetectorCompat
    var scalebleImageViewListener = ScalebleImageViewOnGestureListener()
    var doubleTapListener = DoubleTapListener()
    /*用于自动计算滑动的距离*/
    var overScroll: OverScroller
    /*平滑过渡动画*/
    var scaleAnimator: ObjectAnimator? = null
        get() {
            if (field == null) {
                field = ObjectAnimator.ofFloat(this, "currentFraction",0f)
            }
            //因为smallScaleFraction是动态值
            field!!.setFloatValues(smallScaleFraction, bigScaleFraction)
            return field
        }
    var runnable:ScalableImageRunnable
    /*缩放*/
    var scaleDetectorCompat: ScaleGestureDetector
    var scalableImageScaleGeture = ScalableImageScaleGeture()

    init {
        bitmap = getAvatar(IMAGE_WIDTH.toInt())
        gestureDetector = GestureDetectorCompat(context, scalebleImageViewListener)
        gestureDetector.setOnDoubleTapListener(doubleTapListener)
        overScroll = OverScroller(context)
        runnable = ScalableImageRunnable()
        scaleDetectorCompat = ScaleGestureDetector(context,scalableImageScaleGeture)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        originoffsetX = width / 2f - bitmap.width / 2f
        originoffsetY = height / 2f - bitmap.height / 2f
        if (bitmap.width > bitmap.height) {//宽图
            smallScaleFraction =
                BigDecimal(height).divide(BigDecimal(bitmap.height), 2, BigDecimal.ROUND_HALF_UP)
                    .toFloat()
            bigScaleFraction =
                BigDecimal(width).divide(BigDecimal(bitmap.width), 2, BigDecimal.ROUND_HALF_UP)
                    .toFloat() * OVER_FRACTION
        } else {//长图
            smallScaleFraction =
                BigDecimal(width).divide(BigDecimal(bitmap.width), 2, BigDecimal.ROUND_HALF_UP)
                    .toFloat()
            bigScaleFraction =
                BigDecimal(height).divide(BigDecimal(bitmap.height), 2, BigDecimal.ROUND_HALF_UP)
                    .toFloat() * OVER_FRACTION
        }
        currentFraction = smallScaleFraction
        maxOffsetX = (bitmap.width * bigScaleFraction - width) / 2f
        maxOffsetY = (bitmap.height * bigScaleFraction - height) / 2f
    }

    fun getAvatar(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avtor, options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avtor, options)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.save()
        var scaleFraction = (currentFraction - smallScaleFraction) / (bigScaleFraction - smallScaleFraction)
        canvas.translate(offsetX *scaleFraction, offsetY * scaleFraction)
        canvas.scale(currentFraction, currentFraction, width / 2f, height / 2f)
        canvas.drawBitmap(bitmap, originoffsetX, originoffsetY, paint)
        canvas.restore()
    }

    private fun fixOffsets() {
        offsetX = Math.max(Math.min(offsetX,maxOffsetX),-maxOffsetX)
        offsetY = Math.max(Math.min(offsetY,maxOffsetY),-maxOffsetY)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        var result = scaleDetectorCompat.onTouchEvent(event)
        if (!scaleDetectorCompat.isInProgress){
            result =  gestureDetector.onTouchEvent(event)
        }
        return result
    }

    inner class ScalebleImageViewOnGestureListener : GestureDetector.OnGestureListener {

        /*用户按下100ms不松手后被调用，用于标记【可以显示按下状态了】*/
        override fun onShowPress(e: MotionEvent?) {
        }

        /*用户单击时被调用（支持长按时长按后松手不会调用，双击的第二下不会调用）*/
        override fun onSingleTapUp(e: MotionEvent?): Boolean {
            return false
        }

        /*每次ACTION_DOWN事件出现的时候都会被调用*/
        override fun onDown(e: MotionEvent?): Boolean {
            return true
        }

        /*用户滑动时迅速抬起时被调用，用于用户希望控件进行惯性滑动的场景*/
        override fun onFling(
            e1: MotionEvent?,
            e2: MotionEvent?,
            velocityX: Float,
            velocityY: Float
        ): Boolean {
            if (isBigFraction){
                //启动惯性滑动的计算
                overScroll.fling(
                    offsetX.toInt(), offsetY.toInt(), velocityX.toInt(), velocityY.toInt(), (-maxOffsetX).toInt(),
                    maxOffsetX.toInt(),
                    (-maxOffsetY).toInt(),
                    maxOffsetY.toInt()
                )
                //下一帧刷新
                ViewCompat.postOnAnimation(this@ScalableImageView,runnable)
            }
            return false
        }

        /*用户滑动时被调用，第一个事件是用户按下时的ACTION_DOWN事件，第二个事件是当前事件，偏移量 = 按下时位置-当前事件位置*/
        override fun onScroll(
            e1: MotionEvent?,
            e2: MotionEvent?,
            distanceX: Float,
            distanceY: Float
        ): Boolean {
            if (isBigFraction) {
                offsetX -= distanceX
                offsetY -= distanceY
                Log.e("X",offsetX.toString())
                fixOffsets()
                invalidate()
            }
            return false
        }

        /*用户长按500ms后被调用*/
        override fun onLongPress(e: MotionEvent?) {
        }

    }

    inner class ScalableImageRunnable :Runnable{
        override fun run() {
            //计算此时的位置，并且如果滑动已经结束就停止
            if (overScroll.computeScrollOffset()){
                offsetX = overScroll.currX.toFloat()
                offsetY = overScroll.currY.toFloat()
                invalidate()
                postOnAnimation(this)
            }
        }
    }

    inner class ScalableImageScaleGeture: ScaleGestureDetector.OnScaleGestureListener {
        var initalCurrentScale = 0f
        override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
            initalCurrentScale = currentFraction
            isBigFraction = true
            return true
        }

        override fun onScaleEnd(detector: ScaleGestureDetector?) {

        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            currentFraction = initalCurrentScale *detector.scaleFactor
            /*矫正缩放范围*/
            currentFraction = Math.min(Math.max(currentFraction,smallScaleFraction),bigScaleFraction * OVER_FRACTION)
            invalidate()
            return false
        }

    }

    inner class DoubleTapListener : GestureDetector.OnDoubleTapListener{
        /*用户双击第二次按下时，第二次按下移动后，第二次按下后抬起都会被调用，用于双击拖拽*/
        override fun onDoubleTap(e: MotionEvent): Boolean {
            isBigFraction = !isBigFraction
            /*双击切换缩放因子*/
            if (isBigFraction) {
                /*缩放坐标*/
                offsetX = (e.getX() - width/2f)*(1 - bigScaleFraction / smallScaleFraction)
                offsetY = (e.getY() - height/2f)*(1 - bigScaleFraction / smallScaleFraction)
                fixOffsets()
                scaleAnimator!!.start()
            } else {
                scaleAnimator!!.reverse()
            }
            return false
        }

        /*用户双击时调用，第二次 触摸到屏幕就调用而不是抬起*/
        override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
            return false
        }

        /*用户单击时调用
        * 区别在于用户的一次点击不会立即执行，而是在一定时间后（300ms），确认用户没有进行双击，这个方法才会被调用*/
        override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
            return false
        }
    }

}