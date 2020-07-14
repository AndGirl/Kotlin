package com.ybj.myapplication.kotlin.recyclerView

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.recyclerView.adapter.DiffUtilAdapter
import com.ybj.myapplication.kotlin.recyclerView.commons.Student
import kotlinx.android.synthetic.main.activity_diff_util_recycler_view.*

class DiffUtilRecyclerViewActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    lateinit var data:MutableList<Student>
    lateinit var diffUtilAdapter:DiffUtilAdapter

    private fun initData():MutableList<Student>{
        var data = mutableListOf<Student>()
        data.add(Student("one",1,"a"))
        data.add(Student("two",2,"b"))
        data.add(Student("three",3,"c"))
        data.add(Student("four",4,"d"))
        data.add(Student("five",5,"e"))
        data.add(Student("six",6,"f"))
        return data
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diff_util_recycler_view)

        data = initData()

        diffUtilAdapter = DiffUtilAdapter(data)
        with(list){
            layoutManager = LinearLayoutManager(this@DiffUtilRecyclerViewActivity,
                RecyclerView.VERTICAL,false)
            addItemDecoration(
                DividerItemDecoration(this@DiffUtilRecyclerViewActivity, DividerItemDecoration.VERTICAL)
            )
            adapter = diffUtilAdapter

        }

        swipe_refresh_layout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        val newData = initData()
        newData.removeAt(2)
        newData.add(Student("seven",7,"a"))
        newData.add(Student("oneone",11,"aaa"))
        newData.add(Student("oneoneone",111,"aaaa"))
        newData[0] = Student("one",11,"a")
        diffUtilAdapter.swapData(newData,true)
        swipe_refresh_layout.isRefreshing = false
    }
}
