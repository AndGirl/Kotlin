package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.arithmetic.tools.MyQueue;

import java.util.LinkedList;

/**
 * Created by 杨阳洋 on 2020/7/10.
 * 利用顺序队列，持续新增数据和删除数据
 */
public class Lesson6_1 {

    public static void main(String [] args) throws Exception {
        MyQueue myQueue = new MyQueue(9);
        myQueue.enQueue(0);
        myQueue.enQueue(1);
        myQueue.enQueue(2);
        myQueue.enQueue(3);
        myQueue.enQueue(4);
        myQueue.enQueue(5);
        myQueue.enQueue(6);
        myQueue.enQueue(7);
        myQueue.deQueue();
        myQueue.deQueue();
        myQueue.deQueue();

    }

    public static void ring(int n , int m){
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0 ; i <= n ; i ++){
            linkedList.add(i);
        }
        int k = 2;
        int element = 0;
        int i = 0;
        for (;i<k;i++){
            element = linkedList.poll();
            linkedList.add(element);
        }
        i = 1;
        while (linkedList.size() > 0){
            element = linkedList.poll();
            if(i < m) {
                linkedList.add(element);
                i++;
            }else{
                i = 1;
                System.out.println(element);
            }
        }
    }

}
