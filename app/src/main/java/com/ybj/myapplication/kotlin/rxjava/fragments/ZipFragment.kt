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
import com.ybj.myapplication.kotlin.rxjava.data.ZhuangbiImage
import com.ybj.myapplication.kotlin.utils.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.BiFunction
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_zip.*


/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class ZipFragment : BaseFragment() {

    var itemAdapter = ItemListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_zip, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(gridRv) {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = itemAdapter
        }
        with(swipeRefreshLayout) {
            setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)
            isEnabled = false
        }

        zipLoadBt.setOnClickListener {
            swipeRefreshLayout.isRefreshing = true
            unsubscribe()
            disposable = Observable.zip(HttpClient.getGankApi().getBeauties(200, 1).map(
                GankBeautyResultToItemsMapper
            ),
                HttpClient.ZhuangbiApi().search("装逼"),
                object : BiFunction<List<Item>, List<ZhuangbiImage>, List<Item>> {
                    override fun apply(
                        gankItems: List<Item>,
                        zhuangbiImages: List<ZhuangbiImage>
                    ): List<Item> {
                        var items: MutableList<Item> = mutableListOf()
                        var i = 0
                        while (i < gankItems.size / 2 && i < zhuangbiImages.size) {
                            items.add(gankItems[i * 2])
                            items.add(gankItems[i * 2 + 1])
                            val zhuangbiItem = Item()
                            val (description1, image_url) = zhuangbiImages[i]
                            zhuangbiItem.description = description1
                            zhuangbiItem.imageUrl = image_url
                            items.add(zhuangbiItem)
                            i++
                        }
                        return items
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<List<Item>> { t ->
                    swipeRefreshLayout.isRefreshing = false
                    itemAdapter.images = t
                }, Consumer<Throwable> {
                    swipeRefreshLayout.isRefreshing = false
                    toast(R.string.loading_failed.toString())
                })
        }

    }

    override fun getDialogRes(): Int {
        return R.layout.dialog_zip
    }

    override fun getTitleRes(): Int {
        return R.string.title_zip
    }
}