package com.ybj.myapplication.kotlin.api

import com.ybj.myapplication.kotlin.rxjava.data.FakeThing
import com.ybj.myapplication.kotlin.rxjava.data.FakeToken
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function
import java.lang.Thread.sleep
import java.util.*


/**
 * Created by 杨阳洋 on 2020/8/1.
 */
class FakeApi {
    var random = Random()

    private fun createToken()= "fake_token_" + System.currentTimeMillis() % 10000

    fun getFakeToken(fakeAuth:String):Observable<FakeToken>{
        return Observable.just(fakeAuth)
            .map(Function<String, FakeToken> {
                val fakeNetworkTimeCost = random.nextInt(500) + 500
                try {
                    sleep(fakeNetworkTimeCost.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                val fakeToken = FakeToken()
                fakeToken.token = createToken()
                fakeToken
            })
    }

    fun getFakeData(fakeToken: FakeToken): Observable<FakeThing> {
        return Observable.just(fakeToken)
            .map { (_, expired) ->
                // Add some random delay to mock the network delay
                val fakeNetworkTimeCost = random.nextInt(500) + 500
                try {
                    sleep(fakeNetworkTimeCost.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                require(!expired) { "Token expired!" }
                val fakeData = FakeThing()
                fakeData.id = (System.currentTimeMillis() % 1000).toInt()
                fakeData.name = "FAKE_USER_" + fakeData.id
                fakeData
            }
    }

}