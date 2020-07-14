package com.ybj.myapplication.kotlin.recyclerView.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.recyclerView.commons.Student
import com.ybj.myapplication.kotlin.recyclerView.tools.MyDiffUtil

/**
 * Created by 杨阳洋 on 2020/7/12.
 */
open class DiffUtilAdapter(var list:List<Student>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class DiffUtilViewHolder(v: View):RecyclerView.ViewHolder(v){
        val textView: TextView = v.findViewById(R.id.textView)

        fun onBind(student: Student){
            textView.text = "${student.num} # ${student.name}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item,parent,false)
        return DiffUtilViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        with(holder as DiffUtilViewHolder){
            onBind(list[position])
        }
    }

    fun swapData(newList:List<Student>, diff:Boolean){
        if (diff){
            val diffResult = DiffUtil.calculateDiff(MyDiffUtil(list, newList), true)
            list = newList
            diffResult.dispatchUpdatesTo(this)
        }else{
            list = newList
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()){
            onBindViewHolder(holder,position)
        }else{
            val payload = payloads[0] as Bundle
            for (key in payload.keySet()) {
                when(key){
                    "MSG" -> (holder as DiffUtilViewHolder).onBind(list[position])
                }
            }
        }
    }

}