package com.ybj.myapplication.kotlin.arithemtic

import com.ybj.myapplication.java.arithmetic.tools.SingLinkedList

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
fun main(args: Array<String>): Unit {
    val singLinkedList =
        SingLinkedList()
    singLinkedList.add(1, 0)
    singLinkedList.add(2, 1)
    singLinkedList.add(3, 2)
    singLinkedList.add(4, 3)
    singLinkedList.add(5, 4)
    var slow = singLinkedList.head
    var fast = singLinkedList.head
    while (!(fast?.next == null || fast.next.next == null)) {
        fast = fast.next.next
        slow = slow.next
    }
    val node = slow
}