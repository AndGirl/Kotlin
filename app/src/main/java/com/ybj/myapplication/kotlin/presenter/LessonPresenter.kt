package com.ybj.myapplication.kotlin.presenter

import com.google.gson.reflect.TypeToken
import com.ybj.myapplication.kotlin.LessonActivity
import com.ybj.myapplication.kotlin.data.Lesson
import com.ybj.myapplication.kotlin.http.EntityCallback
import com.ybj.myapplication.kotlin.http.HttpClient
import com.ybj.myapplication.kotlin.utils.toast


/**
 * Created by 杨阳洋 on 2020/6/21.
 */
const val LESSON_PATH = "lessons"
class LessonPresenter(var activity:LessonActivity) {
    var lessons:List<Lesson> = arrayListOf()
    val type = object : TypeToken<List<Lesson>>() {}.type

    fun fetchData(){
        HttpClient.get(LESSON_PATH,type, object: EntityCallback<List<Lesson>> {
            override fun onSuccess(entity: List<Lesson>) {
                activity.runOnUiThread(Runnable {
                    lessons = entity
                    activity.showResult(entity)
                })
            }

            override fun onFailure(message: String) {
                activity.runOnUiThread(Runnable {
                    toast(message)
                })
            }

        })
    }

    fun showPlayback(){
        activity.showResult(lessons.filter {
            it.state == Lesson.State.PLAYBACK
        })
    }

}