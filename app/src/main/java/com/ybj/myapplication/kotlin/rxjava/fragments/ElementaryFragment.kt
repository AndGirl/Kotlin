package com.ybj.myapplication.kotlin.rxjava.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.http.HttpClient
import com.ybj.myapplication.kotlin.rxjava.BaseFragment
import com.ybj.myapplication.kotlin.rxjava.adapters.ZhuangbiListAdapter
import com.ybj.myapplication.kotlin.rxjava.data.ZhuangbiImage
import com.ybj.myapplication.kotlin.utils.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_elementary.*
import kotlinx.android.synthetic.main.tip_bt.*

/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class ElementaryFragment: BaseFragment() {

    var zhuangbiListAdapter = ZhuangbiListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_elementary, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(gridRv){
            layoutManager = GridLayoutManager(activity,2)
            adapter = zhuangbiListAdapter
        }
        with(swipeRefreshLayout){
            setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)
            isEnabled = false
        }
        tipBt.setOnClickListener { tip() }
        radio_group.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId){
                R.id.searchRb1,R.id.searchRb2,R.id.searchRb3,R.id.searchRb4 ->{
                    unsubscribe()
                    zhuangbiListAdapter.images = arrayListOf()
                    swipeRefreshLayout.isRefreshing = true
                    search(checkedId)
                }
            }
        }
    }

    private fun search(key:Int){
        var keyWords:String = ""
        when(key){
            R.id.searchRb1 -> keyWords = "可爱"
            R.id.searchRb2 -> keyWords = "110"
            R.id.searchRb3 -> keyWords = "在下"
            R.id.searchRb4 -> keyWords = "装逼"
        }
        disposable = HttpClient.ZhuangbiApi()
            .search(keyWords)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<List<ZhuangbiImage>> { t ->
                swipeRefreshLayout.isRefreshing = false
                zhuangbiListAdapter.images = t
            }, Consumer<Throwable> {
                swipeRefreshLayout.isRefreshing = false
                toast(R.string.loading_failed.toString())
            })
    }

    override fun getDialogRes(): Int {
        return R.layout.dialog_elementary
    }

    override fun getTitleRes(): Int {
        return R.string.title_elementary
    }
}