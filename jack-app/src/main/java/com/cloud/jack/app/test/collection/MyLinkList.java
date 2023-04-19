package com.cloud.jack.app.test.collection;

public class MyLinkList {

  //定义一个内部双向链表节点类
    private class Node{
        private Object data;
        private Node next;
        private Node prev;
        public Node(Object data){
            this.data = data;
        }
    }

    //定义一个头节点
    private Node head;
    //定义一个尾节点
    private Node last;
    //定义链表的长度
    private int size;

    //添加元素
    public void add(Object obj){

    }

    //指定位置添加元素
    public void add(int index, Object obj){

    }

    //删除元素
    public void remove(Object obj){

    }

    //获取指定位置的元素
    public Object get(int index){

    }

    //获取链表的长度
    public int size(){

    }

    //遍历链表
    public void iterator(){

    }

    //获取指定位置的节点
    private Node node(int index) {
        return null;
    }

}
