package com.ybj.myapplication.kotlin.rxjava

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import io.reactivex.rxjava3.disposables.Disposable

/**
 * Created by 杨阳洋 on 2020/8/1.
 */
abstract class BaseFragment : Fragment() {

    var disposable:Disposable ?= null

    fun tip() = activity?.let {
        AlertDialog.Builder(it)
        .setTitle(getTitleRes())
        .setView(it.layoutInflater.inflate(getDialogRes(),null))
        .show()
    }

    fun unsubscribe(){
        if (disposable != null && !disposable!!.isDisposed){
            disposable!!.dispose()
        }
    }

    abstract fun getDialogRes(): Int

    abstract fun getTitleRes(): Int

}