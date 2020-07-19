package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/16.
 */
class CircleAvatar(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    /*头像宽度*/
    val width = 300f.dp
    /*偏移量*/
    val offset = 50f.dp
    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /*需要展示的头像*/
    lateinit var bitmap:Bitmap
    val xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    /*图形展示区域*/
    lateinit var bounds:RectF
    /*圆形头像半径*/
    val radius = width / 2f
    init{
        bitmap = getAvatar(width.toInt())
        bounds = RectF(offset,offset,offset + width,offset + width)
    }

    fun getAvatar(width:Int) :Bitmap{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avtor,options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources,R.drawable.avtor,options)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawCircle(offset + radius,offset + radius,radius + 10f.dp,paint)
        val saveLayer = canvas.saveLayer(bounds, paint)
        //src
        canvas.drawCircle(offset + radius,offset + radius,radius,paint)
        paint.xfermode = xfermode
        //dest
        canvas.drawBitmap(bitmap,offset,offset,paint)
        paint.xfermode = null

        canvas.restoreToCount(saveLayer)
    }

}