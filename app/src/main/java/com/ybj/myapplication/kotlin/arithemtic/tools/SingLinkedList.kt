package com.ybj.myapplication.kotlin.arithemtic.tools

/**
 * Created by 杨阳洋 on 2020/7/11.
 */
class SingLinkedList {
    class Node(var data: Int) {
        //指针
        var next: Node? = null

    }

    var head: Node? = null

    var last: Node? = null
    var pre: Node? = null
    /**
     * 链表中插入元素
     * @param data:添加元素
     * @param index：添加位置
     */
    fun add(data: Int, index: Int) {
        if (index < 0) {
            throw IndexOutOfBoundsException("超出链表数据范围")
        }
        val node = Node(data)
        val length = length()
        if (head == null) {
            head = node
            head!!.next = null
        } else if (index == length) { //尾部插入
            val lastNode = get(index - 1)
            lastNode!!.next = node
            node.next = null
        } else {
            if (index == 0) {
                node.next = head
                head = node
            } else {
                val preNode = get(index - 1)
                node.next = preNode!!.next
                preNode.next = node
            }
        }
    }

    /**
     * 根据位置删除元素
     * @param index
     * @return
     */
    fun remove(index: Int): Node? {
        if (index < 0) {
            throw IndexOutOfBoundsException("超出链表数据范围")
        }
        val length = length()
        var last: Node? = null
        if (length == 0) {
            return null
        } else if (index == length) { //尾部删除
            val node = get(index - 1)
            last = node!!.next
            node.next = null
        } else {
            if (index == 0) {
                last = head
                head = head!!.next
            } else {
                val preNode = get(index - 1)
                last = preNode!!.next
                preNode.next = preNode.next!!.next
            }
        }
        return last
    }

    /**
     * 链表的长度
     * @return
     */
    private fun length(): Int {
        var length = 0
        if (head == null) {
            return length
        }
        var node = head
        while (node != null) {
            length++
            node = node.next
        }
        return length
    }

    /**
     * 按位置查找元素
     * @param index：获取元素的位置
     * @return
     */
    operator fun get(index: Int): Node? {
        if (index < 0) {
            throw IndexOutOfBoundsException("超出链表数据范围")
        }
        var temp = head
        for (i in 0 until index) {
            temp = temp!!.next
        }
        return temp
    }
}