package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.arithmetic.tools.SingLinkedList;

/**
 * Created by 杨阳洋 on 2020/7/10.
 * 给定一个链表，输出翻转后的链表，例如输入1-2-3-4-5，输出5-4-3-2-1
 */
public class Lesson4_1 {

    public static void main(String [] args){
        SingLinkedList singLinkedList = new SingLinkedList();
        singLinkedList.add(1,0);
        singLinkedList.add(2,1);
        singLinkedList.add(3,2);
        singLinkedList.add(4,3);
        singLinkedList.add(5,4);

        SingLinkedList.Node cur = singLinkedList.head;
        SingLinkedList.Node pre = null;
        SingLinkedList.Node next = null;
        while(cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
}
