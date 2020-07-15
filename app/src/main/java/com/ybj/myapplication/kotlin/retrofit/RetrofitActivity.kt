package com.ybj.myapplication.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.api.Api
import com.ybj.myapplication.kotlin.data.Repo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_retrofit.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()

        val api = retrofit.create(Api::class.java)

        api.listRepos("rengwuxian")
            .enqueue(object :Callback<List<Repo>>{
                override fun onFailure(call: Call<List<Repo>>, t: Throwable) {
                }

                override fun onResponse(call: Call<List<Repo>>, response: Response<List<Repo>>) {
                }

            })


        GlobalScope.launch (Dispatchers.Main){
            try {
                val repos = api.listReposRe("rengwuxian")
                textView.text = repos[0].name
            }catch (e:Exception){
                textView.text = e.message
            }
        }
//        Single.zip<List<Repo>,List<Repo>,String>(
//            api.listReposRx("rengwuxian"),
//            api.listReposRx("google"),
//            BiFunction { t1, t2 ->  "${t1[0].name} - ${t2[0].name}"}
//        ).observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object :SingleObserver<String>{
//                override fun onSuccess(t: String) {
//                    textView.text = t
//                }
//                override fun onSubscribe(d: Disposable?) {
//                }
//                override fun onError(e: Throwable) {
//                    textView.text = e.message
//                }
//
//            })

//        api.listReposRx("rengwuxian")
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(object :SingleObserver<List<Repo>>{
//                override fun onSuccess(t: List<Repo>) {
//                    textView.text = t.get(0).name
//                }
//
//                override fun onSubscribe(d: Disposable?) {
//                }
//
//                override fun onError(e: Throwable) {
//                    textView.text = e.message
//                }
//
//            })

    }
}
