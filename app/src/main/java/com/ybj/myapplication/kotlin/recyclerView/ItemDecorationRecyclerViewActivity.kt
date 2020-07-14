package com.ybj.myapplication.kotlin.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.recyclerView.adapter.DiffUtilAdapter
import com.ybj.myapplication.kotlin.recyclerView.commons.Student
import com.ybj.myapplication.kotlin.recyclerView.tools.TimeItemDecoration
import kotlinx.android.synthetic.main.activity_item_decoration_recycler_view.*

class ItemDecorationRecyclerViewActivity : AppCompatActivity() {

    lateinit var data:MutableList<Student>
    lateinit var diffUtilAdapter:DiffUtilAdapter

    private fun initData():MutableList<Student>{
        var data = mutableListOf<Student>()
        for(count in 0..100){
            data.add(Student("======$count======",count))
        }
        return data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_decoration_recycler_view)

        data = initData()

        diffUtilAdapter = DiffUtilAdapter(data)
        with(list){
            layoutManager = LinearLayoutManager(this@ItemDecorationRecyclerViewActivity,
                RecyclerView.VERTICAL,false)
            addItemDecoration(
                TimeItemDecoration()
            )
            adapter = diffUtilAdapter

        }

    }
}
