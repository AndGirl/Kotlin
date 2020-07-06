package com.ybj.myapplication.kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_practice1.*
import kotlinx.coroutines.*

class PracticeActivity1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_practice1)

        GlobalScope.launch(Dispatchers.Main) {
            val data = async { getData() }
            val processData = async { processData(data.await())  }
            textView.text = processData.await()
        }

    }

    suspend fun getData() = withContext(Dispatchers.IO){
        "uart_erki"
    }

    suspend fun processData(data:String) = withContext(Dispatchers.IO){
        data.split("_")
            .map { it.capitalize() }
            .reduce { acc, s -> acc+s }
    }

}
