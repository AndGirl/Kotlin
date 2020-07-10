package com.ybj.myapplication.kotlin.constraintLayout

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.TransitionManager
import com.ybj.myapplication.R

class ConstraintSetXActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_constraint_set_x)
        setContentView(R.layout.activity_constraint_start)
    }

    fun onClick(view: View) {

        val constraintLayout = view as ConstraintLayout
//        ConstraintSet().apply {
//            clone(constraintLayout)
//            connect(
//                R.id.twitter,
//                ConstraintSet.BOTTOM,
//                ConstraintSet.PARENT_ID,
//                ConstraintSet.BOTTOM
//            )
//        }

        TransitionManager.beginDelayedTransition(constraintLayout)
        val constraintSet = ConstraintSet().apply {
            isForceId = false
            clone(this@ConstraintSetXActivity, R.layout.activity_constraint_end)
        }
        constraintSet.applyTo(constraintLayout)

    }
}
