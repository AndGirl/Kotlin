package com.ybj.myapplication.kotlin

import android.os.Bundle
import android.view.MenuItem
import android.widget.LinearLayout
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.adapter.LessonAdapter
import com.ybj.myapplication.kotlin.base.BaseView
import com.ybj.myapplication.kotlin.data.Lesson
import com.ybj.myapplication.kotlin.presenter.LessonPresenter
import kotlinx.android.synthetic.main.activity_lesson.*


class LessonActivity : AppCompatActivity() ,BaseView<LessonPresenter>,
    Toolbar.OnMenuItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    val lessonPresenter = LessonPresenter(this)
    val lessonAdapter = LessonAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lesson)
        toolbar.inflateMenu(R.menu.menu_lesson)
        toolbar.setOnMenuItemClickListener(this)

        list.layoutManager = LinearLayoutManager(this)
        list.adapter = lessonAdapter
        list.addItemDecoration(DividerItemDecoration(this,LinearLayout.VERTICAL))

        swipe_refresh_layout.setOnRefreshListener(this)
        swipe_refresh_layout.isRefreshing = true
        getPresenter().fetchData()
    }

    fun showResult(lessons: List<Lesson>) {
        lessonAdapter.updateAndNotify(lessons)
        swipe_refresh_layout.isRefreshing = false
    }

    override fun getPresenter(): LessonPresenter {
        return lessonPresenter
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        getPresenter().showPlayback()
        return false
    }

    override fun onRefresh() {
        getPresenter().fetchData()
    }
}
