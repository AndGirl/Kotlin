package com.ybj.myapplication.kotlin.http

import com.google.gson.Gson
import com.ybj.myapplication.kotlin.api.Api
import com.ybj.myapplication.kotlin.api.FakeApi
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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

    fun ZhuangbiApi(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.zhuangbi.info/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        return retrofit.create(Api::class.java)
    }

    fun getGankApi(): Api {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://gank.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        return retrofit.create(Api::class.java)
    }

    fun getFakeApi(): FakeApi {
        return FakeApi()
    }

}