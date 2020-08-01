package com.ybj.myapplication.kotlin.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_view_main.*

class ViewMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_main)

        dash_board.setOnClickListener(this)
        pie_chart.setOnClickListener(this)
        avatar_xfermode.setOnClickListener(this)
        sport_view.setOnClickListener(this)
        break_text.setOnClickListener(this)
        camera_view.setOnClickListener(this)
        animator_view.setOnClickListener(this)
        material_edit_text.setOnClickListener(this)
        tag_layout.setOnClickListener(this)
        scalable_image.setOnClickListener(this)
        multi_touch.setOnClickListener(this)
        view_pager.setOnClickListener(this)
        drag.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.dash_board -> startActivity(Intent(this,DashBoardActivity::class.java))
            R.id.pie_chart -> startActivity(Intent(this,PieChartActivity::class.java))
            R.id.avatar_xfermode -> startActivity(Intent(this,CircleAvatarActivity::class.java))
            R.id.sport_view -> startActivity(Intent(this,SportViewActivity::class.java))
            R.id.break_text -> startActivity(Intent(this,BreakTextActivity::class.java))
            R.id.camera_view -> startActivity(Intent(this,CameraActivity::class.java))
            R.id.animator_view -> startActivity(Intent(this,AnimatorMainActivity::class.java))
            R.id.material_edit_text -> startActivity(Intent(this,MaterialTextViewActivity::class.java))
            R.id.tag_layout -> startActivity(Intent(this,TagLayoutActivity::class.java))
            R.id.scalable_image -> startActivity(Intent(this,ScalableImageActivity::class.java))
            R.id.multi_touch -> startActivity(Intent(this,MultiTouchActivity::class.java))
            R.id.view_pager -> startActivity(Intent(this,ViewPagerActivity::class.java))
            R.id.drag -> startActivity(Intent(this,DragHelperActivity::class.java))
        }
    }
}
