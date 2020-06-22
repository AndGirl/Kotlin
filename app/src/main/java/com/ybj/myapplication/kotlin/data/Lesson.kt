package com.ybj.myapplication.kotlin.data

/**
 * Created by 杨阳洋 on 2020/6/21.
 */
class Lesson(var date: String,var content: String,var state: State) {
    enum class State {
        PLAYBACK {
            override fun stateName() = "Niko"
        },
        LIVE{
            override fun stateName() = "Rain"
        },
        WAIT{
            override fun stateName() = "Coldzera"
        };

        abstract fun stateName(): String
    }

}