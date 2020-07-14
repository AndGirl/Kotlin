package com.ybj.myapplication.java;

import com.google.gson.reflect.TypeToken;
import com.ybj.myapplication.java.genericity.Apple;
import com.ybj.myapplication.java.genericity.AppleShop;
import com.ybj.myapplication.java.genericity.AppleUnicomShop;
import com.ybj.myapplication.java.genericity.Banana;
import com.ybj.myapplication.java.genericity.ChinaUnicom;
import com.ybj.myapplication.java.genericity.CreateGenericity;
import com.ybj.myapplication.java.genericity.Fruit;
import com.ybj.myapplication.java.genericity.Shop;
import com.ybj.myapplication.java.genericity.SimShop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 杨阳洋 on 2020/6/23.
 */
public class Test {

    CreateGenericity<String> genericity = new CreateGenericity();

    Shop<Apple> appleShop = new AppleShop();
    Apple apple = appleShop.buy();
    float money = appleShop.refund(apple);

    HashMap<String ,Integer> hashMap;
    SimShop<Apple, ChinaUnicom> appleChinaUnicomSimShop = new AppleUnicomShop();

//    ArrayList<? extends Fruit> fruits = new ArrayList<Apple>();
//    ArrayList<Apple> apples = new ArrayList<>();
//    Banana fruit = new Banana();
//    Apple otherApple = new Apple();
//    public void fun(){
//        fruits.add(fruit);
//        apples.add(fruit);
//    }


    List<Fruit> fruits =new ArrayList();

    List<Apple> apples = new ArrayList();

    public void test(){
        fruits.add(new Apple());
        fruits.add(new Banana());
        totalWight(apples);
        totalWight(fruits);
        List<? super Apple> applesList = new ArrayList<>();
        List<Fruit> fruitsList = new ArrayList<>();
        applesList.add(new Apple());
        //applesList.addAll(fruitsList);
        Shop<Apple> shop = new Shop<Apple>() {
            @Override
            public Apple buy() {
                return null;
            }

            @Override
            public float refund(Apple item) {
                return 0;
            }

            @Override
            public <E> E refurbish(E item, float money) {
                return null;
            }

            @Override
            public <E extends Runnable & Serializable> void someMethod(E param) {

            }
        };

        shop.refurbish(new Banana(),100f);

        TypeToken<List<String>> token = new TypeToken<List<String>>() {
        };
    }


    public float totalWight(List<? extends Fruit> fruits){
        float weight = 0f;
        for (Fruit f: fruits){
            weight += f.weight();
        }
        return weight;
    }

}
