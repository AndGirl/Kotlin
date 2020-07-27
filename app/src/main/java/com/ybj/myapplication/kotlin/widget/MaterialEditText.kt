package com.ybj.myapplication.kotlin.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp

/**
 * Created by 杨阳洋 on 2020/7/26.
 */
class MaterialEditText(context: Context, attrs: AttributeSet) :
    AppCompatEditText(context, attrs) {
    /*文字大小*/
    val TEXT_SIZE = 12f.dp
    /*文字间间隙*/
    val TEXT_MARGIN = 8f.dp
    /*纵向偏移*/
    val VERTICAL_OFFSET = 38f.dp
    /*横向偏移*/
    val HORIZONTAL_OFFSET = 5f.dp
    /*额外偏移*/
    val EXTRA_OFFSET = 16f.dp
    var paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        this.textSize = TEXT_SIZE
    }
    /*label动画效果*/
    var objectAnimator: ObjectAnimator? = null
    get() {
        if (field == null){
            field = ObjectAnimator.ofFloat(this,"labelFraction",1f)
        }
        return field
    }

    /*标签是否显示*/
    var isLabelShow = false
    var labelFraction = 0f
        set(value) {
            field = value
            invalidate()
        }
    var useLabel = true
    set(value) {
        if (field != value){
            field = value
            refreshPadding()
        }
    }
    var backgroundPaddingRect:Rect = Rect()

    init {
        var typedArray = context.obtainStyledAttributes(
            attrs, intArrayOf(
                android.R.attr.colorAccent
                , android.R.attr.layout_width, R.attr.useLabel
            )
        )

        useLabel = typedArray.getBoolean(2,true)
        typedArray.recycle()

        refreshPadding()
        /*label展示效果*/
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isLabelShow && !TextUtils.isEmpty(s)) {
                    isLabelShow = true
                    objectAnimator!!.start()
                } else if (isLabelShow && TextUtils.isEmpty(s)) {
                    isLabelShow = false
                    objectAnimator!!.reverse()
                }
            }

        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (useLabel) {
            paint.alpha = (labelFraction * 0xff).toInt()
            canvas.drawText(hint.toString(), HORIZONTAL_OFFSET, VERTICAL_OFFSET + -EXTRA_OFFSET * labelFraction, paint)
        }
    }

    private fun refreshPadding(){
        /*设置一个固定值*/
        background.getPadding(backgroundPaddingRect)
        if (useLabel){
            /*预留label标签*/
            setPadding(backgroundPaddingRect.left, (backgroundPaddingRect.top + TEXT_SIZE + TEXT_MARGIN).toInt(), backgroundPaddingRect.right, backgroundPaddingRect.bottom)
        }else{
            /*预留label标签*/
            setPadding(backgroundPaddingRect.left, backgroundPaddingRect.top, backgroundPaddingRect.right, backgroundPaddingRect.bottom)
        }
    }

}