package com.ybj.myapplication.java.arithmetic.tools;

/**
 * Created by 杨阳洋 on 2020/7/14.
 */
public class MyQueue {

    private int [] array;
    private int front;
    private int rear;

    public MyQueue(int size) {
        array = new int[size];
    }

    /**
     * 入队操作
     * @param element
     * @throws Exception
     */
    public void enQueue(int element) throws Exception {
        if ((rear + 1) % array.length == front) {
            throw new Exception("队列已满");
        }
        array[rear] = element;
        rear = (rear + 1) % array.length;
    }

    public int deQueue()throws Exception{
        if(rear == front) {
            throw new Exception("队列为空");
        }
        int deQueue = array[front];
        front = (front + 1) % array.length;
        return deQueue;
    }

}
