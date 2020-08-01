package com.ybj.myapplication.kotlin.rxjava.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.http.HttpClient
import com.ybj.myapplication.kotlin.rxjava.BaseFragment
import com.ybj.myapplication.kotlin.rxjava.adapters.ItemListAdapter
import com.ybj.myapplication.kotlin.rxjava.data.GankBeautyResultToItemsMapper
import com.ybj.myapplication.kotlin.rxjava.data.Item
import com.ybj.myapplication.kotlin.utils.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_map.*
import kotlinx.android.synthetic.main.tip_bt.*

/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class MapFragment : BaseFragment() {

    var itemAdapter = ItemListAdapter()
    var page = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(gridRv){
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter = itemAdapter
        }
        with(swipeRefreshLayout){
            setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)
            isEnabled = false
        }

        tipBt.setOnClickListener { tip() }

        previousPageBt.setOnClickListener {
            loadPage(--page)
            if (page == 1) previousPageBt.isEnabled = false
        }

        nextPageBt.setOnClickListener {
            loadPage(++page)
            if (page == 2) previousPageBt.isEnabled = true
        }

    }

    override fun getDialogRes(): Int {
        return R.layout.dialog_map
    }

    override fun getTitleRes(): Int {
        return R.string.title_map
    }

    fun loadPage(page:Int){
        swipeRefreshLayout.isRefreshing = true
        unsubscribe()
        disposable = HttpClient.getGankApi()
            .getBeauties(0,page)
            .map(GankBeautyResultToItemsMapper)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(Consumer<List<Item>> { t ->
                swipeRefreshLayout.isRefreshing = false
                pageTv.text = getString(R.string.page_with_number,page)
                itemAdapter.images = t
            }, Consumer<Throwable> {
                swipeRefreshLayout.isRefreshing = false
                toast(R.string.loading_failed.toString())
            })
    }

}