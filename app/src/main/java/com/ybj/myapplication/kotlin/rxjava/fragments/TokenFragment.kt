package com.ybj.myapplication.kotlin.rxjava.fragments

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ybj.myapplication.R
import com.ybj.myapplication.kotlin.http.HttpClient
import com.ybj.myapplication.kotlin.rxjava.BaseFragment
import com.ybj.myapplication.kotlin.rxjava.data.FakeThing
import com.ybj.myapplication.kotlin.rxjava.data.FakeToken
import com.ybj.myapplication.kotlin.utils.toast
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Consumer
import io.reactivex.rxjava3.functions.Function
import kotlinx.android.synthetic.main.fragment_token.*

/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class TokenFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_token, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(swipeRefreshLayout) {
            setColorSchemeColors(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW)
            isEnabled = false
        }

        requestBt.setOnClickListener {
            swipeRefreshLayout.isRefreshing = true
            unsubscribe()
            val fakeApi = HttpClient.getFakeApi()
            fakeApi
                .getFakeToken("fake_auth_code")
                .flatMap(object:Function<FakeToken, Observable<FakeThing>>{
                    override fun apply(t: FakeToken): Observable<FakeThing> {
                        return fakeApi.getFakeData(t)
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<FakeThing> { t ->
                    swipeRefreshLayout.isRefreshing = false
                    tokenTv.text = getString(R.string.got_data, t.id, t.name)
                }, Consumer<Throwable> {
                    swipeRefreshLayout.isRefreshing = false
                    toast(R.string.loading_failed.toString())
                })
        }

    }

    override fun getDialogRes(): Int {
        return R.layout.dialog_token
    }

    override fun getTitleRes(): Int {
        return R.string.title_token
    }
}