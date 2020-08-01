package com.ybj.myapplication.java.rxjava;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by 杨阳洋 on 2020/7/30.
 */
public class CreateObservable {

    public void crateFromExisitingDataStructure(){
        Observable<String> stringObservable = Observable.fromArray("a", "b", "c");

        Observable<String> one_object = Observable.just("one object");;
    }

    //同步
    private void createSynchronousObservable(){
    }

}
