package com.ybj.myapplication.kotlin.widgetimport android.content.Contextimport android.graphics.Rectimport android.util.AttributeSetimport android.view.ViewGroup/** * Created by 杨阳洋 on 2020/7/26. */class TagLayout(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {    /*存储每个子Viwe的位置*/    var childViewRect = mutableListOf<Rect>()    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {        var widthUsed = 0        var heightUsed = 0        var lineWidthUsed = 0        var lineHeightUsed = 0        var widthMode = MeasureSpec.getMode(widthMeasureSpec)        var widthSize = MeasureSpec.getSize(widthMeasureSpec)        for(index in 0 until childCount){            val childView = getChildAt(index)            /*测量每个子View的位置*/            measureChildWithMargins(childView,widthMeasureSpec,0,heightMeasureSpec,heightUsed)            /*换行*/            if (widthMode != MeasureSpec.UNSPECIFIED && lineWidthUsed + childView.measuredWidth > widthSize){                lineWidthUsed = 0                heightUsed += lineHeightUsed                measureChildWithMargins(childView,widthMeasureSpec,0,heightMeasureSpec,heightUsed)            }            /*保存位置信息*/            var rect:Rect            if (childViewRect.size <= index){                rect = Rect()                childViewRect.add(rect)            }else{                rect = childViewRect[index]            }            rect.set(lineWidthUsed,heightUsed,lineWidthUsed +childView.measuredWidth,heightUsed +childView.measuredHeight)            lineWidthUsed += childView.measuredWidth            widthUsed = Math.max(lineWidthUsed,widthUsed)            lineHeightUsed = Math.max(lineHeightUsed,childView.measuredHeight)        }        var measuredWidth = widthUsed        heightUsed += lineHeightUsed        var measureHeight = heightUsed        setMeasuredDimension(measuredWidth,measureHeight)    }    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {        return MarginLayoutParams(context,attrs)    }    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {        /*布局每个子View的位置*/        for(index in 0 until childCount){            val childView = getChildAt(index)            val rect = childViewRect[index]            childView.layout(rect.left,rect.top,rect.right,rect.bottom)        }    }}