package com.ybj.myapplication.kotlin.recyclerView

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_recycler_view_main.*

class RecyclerViewMainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view_main)

        button_recyclerview_sample.setOnClickListener(this)
        button_recyclerview_diffutil.setOnClickListener(this)
        button_recyclerview_item_decoration.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.button_recyclerview_sample -> {startActivity(Intent(this,RecyclerViewBaseSampleActivity::class.java))}
            R.id.button_recyclerview_diffutil -> {startActivity(Intent(this,DiffUtilRecyclerViewActivity::class.java))}
            R.id.button_recyclerview_item_decoration -> {startActivity(Intent(this,ItemDecorationRecyclerViewActivity::class.java))}

        }
    }
}
