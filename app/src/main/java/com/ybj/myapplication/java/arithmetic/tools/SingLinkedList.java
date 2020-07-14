package com.ybj.myapplication.java.arithmetic.tools;

/**
 * Created by 杨阳洋 on 2020/7/10.
 * 链表
 */
public class SingLinkedList {
    public static class Node{
        //指针
        public Node next = null;
        public int data;
        public Node(int data) {
            this.data = data;
        }
    }

    public Node head = null;

    public Node last = null;
    public Node pre = null;
    /**
     * 链表中插入元素
     * @param data:添加元素
     * @param index：添加位置
     */
    public void add(int data,int index){
        if(index < 0) {
            throw new IndexOutOfBoundsException("超出链表数据范围");
        }
        Node node = new Node(data);
        int length = length();
        if(head == null) {
            head = node;
            head.next = null;
        }else if(index == length) {
            //尾部插入
            Node lastNode = get(index - 1);
            lastNode.next = node;
            node.next = null;
        }else{
            if(index == 0) {
                node.next = head;
                head = node;
            }else{
                Node preNode = get(index - 1);
                node.next=preNode.next;
                preNode.next = node;
            }
        }
    }

    /**
     * 根据位置删除元素
     * @param index
     * @return
     */
    public Node remove(int index){
        if(index < 0) {
            throw new IndexOutOfBoundsException("超出链表数据范围");
        }
        int length = length();
        Node last = null;
        if(length == 0) {
            return null;
        }else if(index == length) {
            //尾部删除
            Node node = get(index - 1);
            last = node.next;
            node.next = null;
        }else{
            if(index == 0) {
                last = head;
                head = head.next;
            }else{
                Node preNode = get(index - 1);
                last = preNode.next;
                preNode.next = preNode.next.next;
            }
        }
        return last;
    }

    /**
     * 链表的长度
     * @return
     */
    public int length(){
        int length = 0;
        if(head == null) {
            return length;
        }
        Node node = head;
        while (node != null){
            length ++;
            node = node.next;
        }
        return length;
    }

    /**
     * 按位置查找元素
     * @param index：获取元素的位置
     * @return
     */
    public Node get(int index){
        if(index < 0) {
            throw new IndexOutOfBoundsException("超出链表数据范围");
        }
        Node temp = head;
        for (int i = 0 ; i < index ; i++){
            temp = temp.next;
        }
        return temp;
    }


    public static void main(String [] args){
        SingLinkedList singLinkedList = new SingLinkedList();
        singLinkedList.add(1,0);
        singLinkedList.add(2,1);
        singLinkedList.add(3,2);
        singLinkedList.add(4,3);
        singLinkedList.add(5,4);
//        singLinkedList.add(6,5);
//        singLinkedList.add(7,0);
//        singLinkedList.add(8,6);
//        singLinkedList.add(9,1);

//        singLinkedList.remove(0);
//        singLinkedList.remove(2);

//        Node node = singLinkedList.get(singLinkedList.length() - 1);
//        Node node1 = singLinkedList.get(0);
        singLinkedList.length();
    }

}
