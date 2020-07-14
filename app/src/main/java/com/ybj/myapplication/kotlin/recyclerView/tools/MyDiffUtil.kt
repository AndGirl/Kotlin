package com.ybj.myapplication.kotlin.recyclerView.tools

import android.os.Bundle
import androidx.recyclerview.widget.DiffUtil
import com.ybj.myapplication.kotlin.recyclerView.commons.Student

/**
 * Created by 杨阳洋 on 2020/7/12.
 */
class MyDiffUtil(var oldData:List<Student>,var newData:List<Student>): DiffUtil.Callback() {
    /**
     * 这个方法自由定制，在对比数据的时候会被调用,返回ture调用areContentsTheSame
     */
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].num == newData[newItemPosition].num
    }

    /**
     * 旧数据的Size
     */
    override fun getOldListSize() = oldData.size


    /**
     * 新数据的Size
     */
    override fun getNewListSize() = newData.size


    /**
     * areItemsTheSame返回true时，这个方法才会被调用，返回true表明内容相同
     */
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldData[oldItemPosition].num == newData[newItemPosition].num
                && oldData[oldItemPosition].name == newData[newItemPosition].name
                && oldData[oldItemPosition].msg == newData[newItemPosition].msg
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val payload = Bundle()
        if (oldData[oldItemPosition].num != newData[newItemPosition].num){
            payload.putInt("ID",newData[newItemPosition].num)
        }
        if (oldData[oldItemPosition].name != newData[newItemPosition].name){
            payload.putString("NAME",newData[newItemPosition].name)
        }
        if(oldData[oldItemPosition].msg != newData[newItemPosition].msg){
            payload.putString("MSG",newData[newItemPosition].msg)
        }
        if (payload.size() == 0) return null

        return payload
    }
}