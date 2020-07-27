package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp


/**
 * Created by 杨阳洋 on 2020/7/20.
 */
class BreakTextView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    val longStr = "leftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleft" +
            "leftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleftleft"

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    /*需要展示的头像*/
    lateinit var bitmap: Bitmap
    /*头像宽度*/
    val imgWidth = 300f.dp
    val imageOffset = 120f.dp
    var fontMetrics = Paint.FontMetrics()
    var cutWidth = FloatArray(1)
    init{
        bitmap = getAvatar(imgWidth.toInt())
        paint.getFontMetrics(fontMetrics)
        paint.textSize = 45f.dp
    }

    private fun getAvatar(width:Int) :Bitmap{
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avtor,options)
        options.inJustDecodeBounds = false
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        return BitmapFactory.decodeResource(resources, R.drawable.avtor,options)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap,width - imgWidth,imageOffset,paint)
        var length = longStr.length
        //两行文字baseLine的间距
        var fontSpacing= paint.fontSpacing
        var verticalOffset = fontSpacing
        var start = 0
        var count = 0
        while (start < length){
            //可用的最大宽度
            var usableWidth = 0f
            //一行文本高度
            var textTop = verticalOffset + fontMetrics.top
            var textBottom = verticalOffset + fontMetrics.bottom
            if ((textTop > imageOffset && textTop < (imageOffset + imgWidth)) ||
                (textBottom > imageOffset && textBottom < (imageOffset + imgWidth))){
                usableWidth = width - imgWidth
            }else{
                usableWidth = width.toFloat()
            }
            //截多少个字符
            count = paint.breakText(longStr,start,length,true,usableWidth,cutWidth)

            canvas.drawText(longStr,start,start + count,0f,verticalOffset,paint)
            start += count
            verticalOffset += fontSpacing
        }
    }

}