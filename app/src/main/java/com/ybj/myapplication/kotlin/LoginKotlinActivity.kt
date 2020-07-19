package com.ybj.myapplication.kotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.data.User
import com.ybj.myapplication.kotlin.utils.CacheUtils
import com.ybj.myapplication.kotlin.utils.toast
import com.ybj.myapplication.kotlin.widget.CodeView
import kotlinx.android.synthetic.main.activity_login_kotlin.*


class LoginKotlinActivity : AppCompatActivity(), View.OnClickListener {

    val usernameKey = "username"
    val passwordKey = "password"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_kotlin)

        drawBadge()

        CacheUtils.get(usernameKey)
        login_username.setText(CacheUtils.get(usernameKey))
        login_password.setText(CacheUtils.get(passwordKey))

        login_codeviewimg.updateCode()
        login_codeviewimg.setOnClickListener(this)
        login.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v){
            is CodeView -> v.updateCode()
            else -> login()
        }
    }

    fun login(){
        val user = User(
            login_username.text.toString(),
            login_password.text.toString(),
            login_codeview.text.toString()
        )

        if (verify(user)){
            CacheUtils.save(usernameKey,login_username.text.toString())
            CacheUtils.save(passwordKey,login_password.text.toString())
            startActivity(Intent(this,LessonActivity::class.java))
        }

    }

    private fun drawBadge(){
        val decorView: ViewGroup = window.decorView as ViewGroup
        val badge:View = View(this)
        badge.setBackgroundColor(Color.BLUE)
        decorView.addView(badge,200,200)
    }

    fun verify(user:User):Boolean{
         when{
             user.userName?.length?:0 < 4 -> {
                 toast("用户名不合法")
                 return false
             }
             user.password?.length?:0 < 4 ->{
                 toast("密码不合法")
                 return false
             }
             else -> return true
         }
    }

}
