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
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.dash_board -> startActivity(Intent(this,DashBoardActivity::class.java))
            R.id.pie_chart -> startActivity(Intent(this,PieChartActivity::class.java))
            R.id.avatar_xfermode -> startActivity(Intent(this,CircleAvatarActivity::class.java))
        }
    }
}
