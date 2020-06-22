package com.ybj.myapplication.kotlin.base

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
open class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val viewHashMap = SparseArray<View>()

    fun <T:View> getView(id:Int): T {
        return when(val view = viewHashMap.get(id)){
            null -> itemView.findViewById<T>(id).also {
                viewHashMap.put(id,it)
            }
            else -> view as T
        }
    }

    fun setText(id:Int,text:String){
        getView<TextView>(id).text = text
    }

}