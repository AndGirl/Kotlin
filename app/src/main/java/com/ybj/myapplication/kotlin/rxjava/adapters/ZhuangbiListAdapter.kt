package com.ybj.myapplication.kotlin.rxjava.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.rxjava.data.ZhuangbiImage

/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class ZhuangbiListAdapter : RecyclerView.Adapter<ZhuangbiListAdapter.DebounceViewHolder>() {

    var images: List<ZhuangbiImage> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class DebounceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.descriptionTv)
        private val imageView: ImageView = itemView.findViewById(R.id.imageIv)

        fun bindData(data:ZhuangbiImage){
            textView.text = data.description
            Glide.with(itemView.context).load(data.image_url).into(imageView)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebounceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_item, parent, false)
        return DebounceViewHolder(view)
    }

    override fun getItemCount()= images.size

    override fun onBindViewHolder(holder: DebounceViewHolder, position: Int) {
        holder.bindData(images[position])
    }

}