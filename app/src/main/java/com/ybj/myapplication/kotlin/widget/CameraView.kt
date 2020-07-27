package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp
import com.ybj.myapplication.kotlin.utils.getZForCamera

/**
 * Created by 杨阳洋 on 2020/7/20.
 */
class CameraView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    lateinit var bitmap:Bitmap
    /*头像宽度*/
    val imgWidth = 200f.dp
    val imgOffset = 100f.dp

    var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    var camera = Camera()

    init {
        bitmap = getAvatar(imgWidth.toInt())
        camera.rotateX(30f)
        camera.setLocation(0f,0f, getZForCamera())
    }

    private fun getAvatar(width:Int) : Bitmap {
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

        canvas.drawBitmap(bitmap,0f,0f,paint)

        canvas.save()
        canvas.translate(width / 2f,height / 2f)
        canvas.rotate(-30f)
        //图片的中心局域View正中心
        canvas.clipRect(-imgWidth,-imgWidth,imgWidth,0f)
        canvas.rotate(30f)
        canvas.translate(-width / 2f,-height / 2f)
        canvas.drawBitmap(bitmap,width/2f - imgOffset,height/2f-imgOffset,paint)
        canvas.restore()



        canvas.save()
        canvas.translate(width / 2f,height / 2f)
        canvas.rotate(-30f)
        //图片的中心局域View正中心
        camera.applyToCanvas(canvas)
        canvas.clipRect(-imgWidth,0f,imgWidth,imgWidth)
        canvas.rotate(30f)
        canvas.translate(-width / 2f,-height / 2f)
        canvas.drawBitmap(bitmap,width/2f - imgOffset,height/2f-imgOffset,paint)
        canvas.restore()
    }

}