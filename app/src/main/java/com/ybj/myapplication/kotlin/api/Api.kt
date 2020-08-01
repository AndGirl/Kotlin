package com.ybj.myapplication.kotlin.api

import com.ybj.myapplication.kotlin.data.Repo
import com.ybj.myapplication.kotlin.rxjava.data.GankBeautyResult
import com.ybj.myapplication.kotlin.rxjava.data.ZhuangbiImage
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by 杨阳洋 on 2020/7/6.
 */
interface Api {
    //Retrofit搭配协程
    @GET("users/{user}/repos")
    suspend fun listReposRe(@Path("user")user:String):List<Repo>

    //RxJava搭配协程
    @GET("users/{user}/repos")
    fun listReposRx(@Path("user")user:String):Single<List<Repo>>

    //Retrofit搭配协程
    @GET("users/{user}/repos")
    fun listRepos(@Path("user")user:String):Call<List<Repo>>

    /*装逼图片*/
    @GET("search")
    fun search(@Query("q")query:String):Observable<List<ZhuangbiImage>>

    @GET("data/福利/{number}/{page}")
    fun getBeauties(@Path("number") number:Int,@Path("page") page:Int):Observable<GankBeautyResult>

}