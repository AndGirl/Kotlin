package com.ybj.myapplication.kotlin.okhttp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import okhttp3.*
import java.io.IOException
import java.net.InetAddress
import kotlin.concurrent.thread

class OkHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http)


        val url = "https://api.github.com/users/rengwuxian/repos"
        val client = OkHttpClient.Builder()
            .authenticator(object:Authenticator{
                override fun authenticate(route: Route?, response: Response): Request? {
                    //token刷新
                    return response.request.newBuilder()
                        .header("Authorization","xxxxxx")
                        .build()
                }
            }).build()
        val request = Request.Builder()
            .url(url)
            .build()
        client.newCall(request)
            .enqueue(object:Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    println("Response status code: ${response.code}")
                }
            })

        thread {
            println("Resoved address:${InetAddress.getAllByName("hencoder.com")[0]}")
        }

    }
}
