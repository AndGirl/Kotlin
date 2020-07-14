package com.ybj.myapplication.kotlin.recyclerView.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.text_row_item.view.*

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
class CustomAdapter (private val dataSet:Array<String>):
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    var count = 0

    class ViewHolder(v:View):RecyclerView.ViewHolder(v) {
        private val textView:TextView
        init {
            v.setOnClickListener{
                Log.d(TAG, "$adapterPosition clicked.")
            }
            textView = v.findViewById(R.id.textView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("onCreateViewHolder","${count++}")
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")
        holder.itemView.textView.text= dataSet[position]
    }

    companion object {
        private const val TAG = "CustomAdapter"
    }
}