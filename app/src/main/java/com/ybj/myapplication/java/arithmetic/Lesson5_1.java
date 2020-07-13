package com.ybj.myapplication.java.arithmetic;

import com.ybj.myapplication.java.arithmetic.tools.SingLinkedList;

/**
 * Created by 杨阳洋 on 2020/7/10.
 * 给定一个奇数个元素的链表，查找出这个链表中间位置的结点的数值。
 */
public class Lesson4_2 {

    public static void main(String [] args){
        SingLinkedList singLinkedList = new SingLinkedList();
        singLinkedList.add(1,0);
        singLinkedList.add(2,1);
        singLinkedList.add(3,2);
        singLinkedList.add(4,3);
        singLinkedList.add(5,4);

        SingLinkedList.Node slow = singLinkedList.head;
        SingLinkedList.Node fast = singLinkedList.head;

        while(fast!=null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        SingLinkedList.Node node = slow;
    }
}
