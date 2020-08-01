package com.ybj.myapplication.kotlin.rxjava.fragments

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
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_token_advanced.*


/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class TokenAdvancedFragment : BaseFragment() {

    var cachedFakeToken = FakeToken("", false)
    var tokenUpdated = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_token_advanced, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requestBt.setOnClickListener {
            tokenUpdated = false
            unsubscribe()
            val fakeApi = HttpClient.getFakeApi()
            disposable = Observable.just(1)
                .flatMap(object : Function<Any, Observable<FakeThing>> {
                    override fun apply(t: Any): Observable<FakeThing> {
                        return if (cachedFakeToken.token.isEmpty()) {
                            Observable.error(NullPointerException("Token is null!"))
                        } else {
                            fakeApi.getFakeData(cachedFakeToken)
                        }
                    }
                })
                .retryWhen(object : Function<Observable<in Throwable>, Observable<Any>> {
                    override fun apply(throwable: Observable<in Throwable>): Observable<Any> {
                        if (throwable is IllegalArgumentException || throwable is NullPointerException) {
                            fakeApi.getFakeToken("fake_auth_code")
                                .doOnNext { (token, expired) ->
                                    tokenUpdated = true
                                    cachedFakeToken.token = token
                                    cachedFakeToken.expired = expired
                                }
                        }
                        return Observable.error(NullPointerException("fake_auth_code is null!"))
                    }

                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(Consumer<FakeThing> { t ->
                    swipeRefreshLayout.isRefreshing = false
                    var token = cachedFakeToken.token
                    if (tokenUpdated) {
                        token += "(" + getString(R.string.updated) + ")"
                    }
                    tokenTv.text = getString(R.string.got_data, t.id, t.name)
                }, Consumer<Throwable> {
                    swipeRefreshLayout.isRefreshing = false
                    toast(R.string.loading_failed.toString())
                })
        }
    }

    override fun getDialogRes(): Int {
        return R.layout.dialog_token_advanced
    }

    override fun getTitleRes(): Int {
        return R.string.title_token_advanced
    }
}