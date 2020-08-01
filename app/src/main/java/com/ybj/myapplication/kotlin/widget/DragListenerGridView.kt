package com.ybj.myapplication.kotlin.widget

import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.DragEvent
import android.view.View
import android.view.ViewConfiguration
import android.view.ViewGroup


/**
 * Created by 杨阳洋 on 2020/7/30.
 * DragListener练习
 */
class DragListenerGridView(context: Context?, attrs: AttributeSet?) : ViewGroup(context, attrs) {
    private val COLUMNS = 2
    private val ROWS = 3
    val onDragListener = DragListener()
    val viewConfiguration = ViewConfiguration.get(context)
    lateinit var draggedView:View
    var orderedChildren  = mutableListOf<View>()

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
//            childView.layout(childLeft,childTop,childLeft + childWidth,childTop + childHeight)
            childView.layout(0,0,childWidth,childHeight)
            childView.translationX = childLeft.toFloat()
            childView.translationY = childTop.toFloat()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        for (index in 0 until childCount){
            val view = getChildAt(index)
            orderedChildren.add(view)
            view.setOnLongClickListener(object:OnLongClickListener{
                override fun onLongClick(v: View): Boolean {
                    draggedView = v
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        v.startDragAndDrop(null,DragShadowBuilder(v),v,0)
                    }else{
                        v.startDrag(null,DragShadowBuilder(v),v,0)
                    }
                    return false
                }
            })
            view.setOnDragListener(onDragListener)
        }
    }

    inner class DragListener : OnDragListener{
        override fun onDrag(v: View, event: DragEvent): Boolean {
            when(event.action){
                DragEvent.ACTION_DRAG_STARTED ->{
                    if (event.localState == v) v.visibility = View.INVISIBLE
                }
                DragEvent.ACTION_DRAG_ENTERED ->{
                    if (event.localState != v) sort(v)
                }
                DragEvent.ACTION_DRAG_ENDED ->{
                    if (event.localState == v) v.visibility = View.VISIBLE
                }
                DragEvent.ACTION_DRAG_EXITED ->{

                }
            }
            return true
        }
    }


    private fun sort(targetView: View) {
        var draggedIndex = -1
        var targetIndex = -1
        for (i in 0 until childCount) {
            val child = orderedChildren[i]
            if (targetView === child) {
                targetIndex = i
            } else if (draggedView === child) {
                draggedIndex = i
            }
        }
        orderedChildren.removeAt(draggedIndex)
        orderedChildren.add(targetIndex, draggedView)
        var childLeft: Int
        var childTop: Int
        val childWidth = width / COLUMNS
        val childHeight = height / ROWS
        for (index in 0 until childCount) {
            val child = orderedChildren[index]
            childLeft = index % 2 * childWidth
            childTop = index / 2 * childHeight
//            child.layout(childLeft,childTop,childLeft + childWidth,childTop + childHeight)

            child.animate()
                .translationX(childLeft.toFloat())
                .translationY(childTop.toFloat()).duration = 150
        }
    }
}