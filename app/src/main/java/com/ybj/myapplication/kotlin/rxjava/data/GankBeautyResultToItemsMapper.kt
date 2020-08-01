package com.ybj.myapplication.kotlin.rxjava.data

import io.reactivex.rxjava3.functions.Function
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by 杨阳洋 on 2020/8/2.
 */
object GankBeautyResultToItemsMapper : Function<GankBeautyResult, List<Item>> {

    override fun apply(gankBeautyResult: GankBeautyResult): List<Item> {
        val gankBeauties = gankBeautyResult.beauties
        val items: MutableList<Item> =
            ArrayList(gankBeauties.size)
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS'Z'")
        val outputFormat = SimpleDateFormat("yy/MM/dd HH:mm:ss")
        for ((createdAt, url) in gankBeauties) {
            val item = Item()
            try {
                val date: Date = inputFormat.parse(createdAt)
                item.description = outputFormat.format(date)
            } catch (e: ParseException) {
                e.printStackTrace()
                item.description = "unknown date"
            }
            item.imageUrl = url
            items.add(item)
        }
        return items

    }


}