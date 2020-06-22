package com.ybj.myapplication.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.base.BaseViewHolder
import com.ybj.myapplication.kotlin.data.Lesson

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
class LessonAdapter :RecyclerView.Adapter<LessonAdapter.LessonViewHolder>(){

    class LessonViewHolder(itemView:View):BaseViewHolder(itemView){
        companion object{
            fun onCreate(parent:ViewGroup):LessonViewHolU er{
                return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson,parent,false))
            }
        }

        fun onBind(lesson:Lesson){
            setText(R.id.tv_date,lesson.date?:"日期待定")
            setText(R.id.tv_content,lesson.content?:"Blast")
            lesson.state.let {
                setText(R.id.tv_state,lesson.state.stateName())
                val color = when (it) {
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                }
                val backgroundColor = itemView.context.getColor(color)
                getView<View>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }
        }

    }

    var list:List<Lesson> = ArrayList()

    fun updateAndNotify(list:List<Lesson>){
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list.get(position))
    }

}