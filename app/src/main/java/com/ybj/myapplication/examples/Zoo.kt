package com.ybj.myapplication.examples

/**
 * Created by 杨阳洋 on 2020/6/20.
 */
class Zoo(val animals:List<Animal>) {
    operator fun iterator():Iterator<Animal>{
        return animals.iterator()
    }
}