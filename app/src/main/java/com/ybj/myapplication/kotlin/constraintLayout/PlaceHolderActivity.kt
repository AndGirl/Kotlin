package com.ybj.myapplication.kotlin.constraintLayout

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.TransitionManager
import com.ybj.myapplication.R
import kotlinx.android.synthetic.main.activity_place_holder.*

class PlaceHolderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_holder)
    }

    fun onClick(view :View){
        TransitionManager.beginDelayedTransition(view.parent as ViewGroup)
        placeholder.setContentId(view.id)
    }

}
