package com.ybj.myapplication.kotlin.constraintLayout

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.os.Bundle
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_circular_positioning.*

class CircularPositioningActivity : AppCompatActivity() {

    private val earthAnimation: ValueAnimator by lazy {
        ValueAnimator.ofFloat(0f, 1f)
            .apply {
                duration = 10000L
                repeatCount = INFINITE
                interpolator = LinearInterpolator()
                addUpdateListener {
                    val params = earth.layoutParams as ConstraintLayout.LayoutParams
                    params.circleAngle = 45 + it.animatedFraction * 360
                    earth.requestLayout()
                }
            }
    }

    private val moonAnimator: ValueAnimator by lazy {
        ValueAnimator.ofFloat(0f, 1f)
            .apply {
                duration = 2000L
                repeatCount = INFINITE
                interpolator = LinearInterpolator()
                addUpdateListener {
                    val params = moon.layoutParams as ConstraintLayout.LayoutParams
                    params.circleAngle = 270 + it.animatedFraction * 360
                    moon.requestLayout()
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circular_positioning)

        createAnimation()
        sun.setOnClickListener {
            earthAnimation.start()
            moonAnimator.start()
        }

    }

    private fun createAnimation() {
//        earthAnimation = ValueAnimator.ofFloat(0f,1f)
//            .apply {
//                duration = 10000L
//                repeatCount = INFINITE
//                interpolator = LinearInterpolator()
//                addUpdateListener {
//                    val params = earth.layoutParams as ConstraintLayout.LayoutParams
//                    params.circleAngle = 45 + it.animatedFraction * 360
//                }
//            }
//
//        moonAnimator = ValueAnimator.ofFloat(0f,1f)
//            .apply {
//                duration = 20000L
//                repeatCount = INFINITE
//                interpolator = LinearInterpolator()
//                addUpdateListener {
//                    val params = moon.layoutParams as ConstraintLayout.LayoutParams
//                    params.circleAngle = 270 + it.animatedFraction * 360
//                }
//            }

    }

}
