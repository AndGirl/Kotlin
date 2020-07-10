package com.ybj.myapplication.kotlin.constraintLayout.tools

import android.content.Context
import android.util.AttributeSet
import android.view.ViewAnimationUtils
import androidx.constraintlayout.widget.ConstraintHelper
import androidx.constraintlayout.widget.ConstraintLayout

/**
 * Created by 杨阳洋 on 2020/7/10.
 */
class CircularRevealHelper(context: Context,attrs:AttributeSet) :ConstraintHelper(context,attrs){

    override fun updatePostLayout(container: ConstraintLayout?) {
        super.updatePostLayout(container)

        for(referenceId in referencedIds){
            val view = container!!.getViewById(referenceId)
            val radius = Math.hypot(view.width.toDouble(), view.height.toDouble()).toInt()
            ViewAnimationUtils.createCircularReveal(view,0,0,0f,radius.toFloat())
                .setDuration(2000L)
                .start()
        }

    }

}