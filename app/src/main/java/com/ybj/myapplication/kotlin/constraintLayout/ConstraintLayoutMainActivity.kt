package com.ybj.myapplication.kotlin.constraintLayout

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_constraint_layout_main.*

class ConstraintLayoutMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_constraint_layout_main)

        clickListener()

    }

    private fun clickListener(){
        button_sample.setOnClickListener(this)
        button_circular_positioning.setOnClickListener(this)
        button_dimension_ratio.setOnClickListener(this)
        button_percent.setOnClickListener(this)
        button_guide_line.setOnClickListener(this)
        button_helper.setOnClickListener(this)
        button_circular_reveal.setOnClickListener(this)
        button_place_holder.setOnClickListener(this)
        button_constraint_setX.setOnClickListener(this)
        button_linear_activity.setOnClickListener(this)
        button_flow_activity.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button_sample -> {
                startActivity(Intent(this,ConstraintSampleActivity::class.java))
            }
            R.id.button_circular_positioning ->{
                startActivity(Intent(this,CircularPositioningActivity::class.java))
            }
            R.id.button_dimension_ratio ->{
                startActivity(Intent(this,DimensionRatioActivity::class.java))
            }
            R.id.button_percent ->{
                startActivity(Intent(this,ButtonPercentActivity::class.java))
            }
            R.id.button_guide_line ->{
                startActivity(Intent(this,GuideLineActivity::class.java))
            }
            R.id.button_helper ->{
                startActivity(Intent(this,HelperActivity::class.java))
            }
            R.id.button_circular_reveal ->{
                startActivity(Intent(this,CircularRevealActivity::class.java))
            }
            R.id.button_place_holder ->{
                startActivity(Intent(this,PlaceHolderActivity::class.java))
            }
            R.id.button_constraint_setX ->{
                startActivity(Intent(this,ConstraintSetXActivity::class.java))
            }
            R.id.button_linear_activity ->{
                startActivity(Intent(this,LinearActivity::class.java))
            }
            R.id.button_flow_activity ->{
                startActivity(Intent(this,FlowActivity::class.java))
            }
        }
    }

}
