package com.ybj.myapplication.kotlin.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
object HttpClient: OkHttpClient() {

    val gson = Gson()

    fun<T> convert(json:String,type:Type) = gson.fromJson<T>(json,type)

    fun<T> get(path:String,type:Type,entityCallback:EntityCallback<T>){
        val request = Request.Builder()
            .url("https:api.hencoder.com/$path")
            .build()

        this.newCall(request = request)
            .enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {
                    entityCallback.onFailure("网络异常")
                }

                override fun onResponse(call: Call, response: Response) {
                    when(response.code){
                        in 200..299 -> entityCallback.onSuccess(convert<T>(response.body!!.string(),type))
                        in 400..499 -> entityCallback.onFailure("客户端错误")
                        in 500..599 -> entityCallback.onFailure("服务器错误")
                        else -> entityCallback.onFailure("未知错误")
                    }
                }

            })

    }

}