package com.ybj.myapplication.kotlin.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.graphics.PointF
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.utils.dp
import com.ybj.myapplication.kotlin.utils.toast
import kotlinx.android.synthetic.main.activity_animator_main.*

class AnimatorMainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animator_main)

        //ViewPropertyAnimator
        img.animate()
            .translationX(120f.dp)
            .setDuration(1000L)
            .start()
        with(ObjectAnimator.ofFloat(circle_view, "radius", 200f.dp)){
            duration =2000L
            startDelay = 1000L
            start()
        }

        var topFlip = ObjectAnimator.ofFloat(camera_view, "topFlip", 30f)
        topFlip.startDelay = 1000L


        var bottomFlip = ObjectAnimator.ofFloat(camera_view, "bottomFlip", 30f)
        bottomFlip.startDelay = 1000L

        var flipRotate = ObjectAnimator.ofFloat(camera_view, "flipRotate", 30f)
        flipRotate.startDelay = 1000L

        with(AnimatorSet()){
            playSequentially(topFlip,bottomFlip,flipRotate)
            duration = 2000L
            start()
        }

//        val keyframe = Keyframe.ofFloat(0f, 15f)
//        val keyframe1 = Keyframe.ofFloat(0.5f, 25f)
//        val keyframe2 = Keyframe.ofFloat(1f, 45f)
//        val ofKeyframe = PropertyValuesHolder.ofKeyframe("radius", keyframe, keyframe1, keyframe2)
//        ObjectAnimator.ofPropertyValuesHolder(circle_view,ofKeyframe)
//            .start()

//        val bottomFlip = PropertyValuesHolder.ofFloat("bottomFlip", 45f)
//        val topFlip = PropertyValuesHolder.ofFloat("topFlip", 45f)
//        val flipRotate = PropertyValuesHolder.ofFloat("flipRotate", 45f)
//        val propertyValuesHolder =
//            ObjectAnimator.ofPropertyValuesHolder(camera_view, bottomFlip, topFlip, flipRotate)
//        propertyValuesHolder.duration = 1000L
//        propertyValuesHolder.startDelay = 1000L
//        propertyValuesHolder.start()

        val pointF = PointF(300f, 300f)
        val animator = ObjectAnimator.ofObject(point_view, "pointF", PointFEvaluator(), pointF)
        animator.startDelay = 2000L
        animator.duration = 2000L
        animator.start()

        circle_view.setOnClickListener {
            toast("点击了")
        }

    }

    class PointFEvaluator :TypeEvaluator<PointF>{
        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            var x = startValue.x + (endValue.x - startValue.x) * fraction
            var y = startValue.y + (endValue.y - startValue.y) * fraction
            return PointF(x,y)
        }
    }

}
