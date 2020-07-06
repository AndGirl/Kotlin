package com.ybj.myapplication.kotlin.handler

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_handler_thread.*


class HandlerThreadActivity : AppCompatActivity() {

    lateinit var mainHandler:Handler
    lateinit var workHandler:Handler
    lateinit var handlerThread:HandlerThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_handler_thread)

        //创建与主线程有关的Handler
        mainHandler = Handler()
        //创建HandlerThread实例对象
        handlerThread = HandlerThread("handlerThread")
        //启动线程
        handlerThread.start()

        workHandler = object : Handler(handlerThread.looper){
            override fun handleMessage(msg: Message) {
                when(msg.what){
                    1 -> {
                        try {
                            Thread.sleep(1000)
                        }catch (e:InterruptedException){
                            e.printStackTrace()
                        }
                        mainHandler.post { text1.text = "我爱学习" }
                    }

                    2 ->{
                        try {
                            Thread.sleep(3000)
                        }catch (e:InterruptedException){
                            e.printStackTrace()
                        }
                        mainHandler.post { text1.text = "我bu爱学习" }
                    }
                }
            }
        }

        button1.setOnClickListener {
            val message = Message.obtain()
            message.what = 1
            message.obj = "A"
            workHandler.sendMessage(message)
        }

        button2.setOnClickListener {
            val message = Message.obtain()
            message.what = 2
            message.obj = "B"
            workHandler.sendMessage(message)
        }

        button3.setOnClickListener {
            handlerThread.quit()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        handlerThread.quit()
    }

}
