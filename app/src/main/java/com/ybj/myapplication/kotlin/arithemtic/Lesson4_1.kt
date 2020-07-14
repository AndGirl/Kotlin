package com.ybj.myapplication.kotlin.arithemtic

import com.ybj.myapplication.java.arithmetic.tools.SingLinkedList

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
class Lesson4_1 {

}
fun main() {
    val singLinkedList = SingLinkedList()
    singLinkedList.add(1, 0)
    singLinkedList.add(2, 1)
    singLinkedList.add(3, 2)
    singLinkedList.add(4, 3)
    singLinkedList.add(5, 4)
    var cur =
        singLinkedList.head
    var pre: SingLinkedList.Node? = null
    var next: SingLinkedList.Node? = null
    while (cur != null) {
        next = cur.next
        cur.next = pre
        pre = cur
        cur = next
    }
}