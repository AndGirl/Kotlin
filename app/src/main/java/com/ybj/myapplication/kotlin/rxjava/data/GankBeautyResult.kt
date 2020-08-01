package com.ybj.myapplication.kotlin.rxjava.data

import com.google.gson.annotations.SerializedName

/**
 * Created by 杨阳洋 on 2020/8/1.
 */
data class GankBeautyResult(var error:Boolean,@SerializedName("results")var beauties:List<GankBeauty>)