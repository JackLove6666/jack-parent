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

        public Node(Object data, Node next, Node prev){
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
        public Node() {
           super();
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
        //创建节点
        Node node = new Node(obj);
        //判断链表是否为空
        if(head ==null){
            head = node;
            last = node;
            //第一个元素的前一个节点和后一个节点都为null，所以不需要赋值，默认为null，所以注释掉
//            head.prev = null;
//            last.next = null;
        }else {
            //将新节点添加到链表的尾部
            last.next = node;
            //将新节点的前一个节点指向原来的尾节点
            node.prev = last;
            //将链表的尾节点指向新节点
            last = node;
            //最后一个元素的后一个节点为null，所以不需要赋值，默认为null，所以注释掉
            //last.next = null;
        }
        size++;
    }

    //指定位置添加元素
    public void add(int index, Object obj){
        //判断索引是否合法
        if(index < 0 || index > size){
            throw new RuntimeException("索引不合法");
        }
        //创建节点
        Node node = new Node(obj);
        //判断链表是否为空
        if(head == null) {
            head = node;
            last = node;
        }else {
            //获取指定位置的节点
            Node temp = node(index);
            //获取指定位置的前一个节点
            Node prev = temp.prev;
            //将新节点的前一个节点指向指定位置的前一个节点
            node.prev = prev;
            //将新节点的后一个节点指向指定位置的节点
            node.next = temp;
            //将指定位置的前一个节点的后一个节点指向新节点
            temp.prev = node;
            //将指定位置的前一个节点指向新节点
            prev.next = node;
        }
    }

    //删除元素
    public void remove(Object obj){
        //判断链表是否为空
        if(head == null){
            throw new RuntimeException("链表为空");
        }
        //判断是否为空
        if(obj == null){
            for(Node x = head; x != null;x = head.next){
                if(x.data == null){
                    //获取头节点的后一个节点
                    Node next = x.next;
                    //将头节点的后一个节点的前一个节点指向null
                    next.prev = null;
                    //将头节点的后一个节点指向头节点
                    head = next;
                    size--;
                    return;
                }
            }
        }else {
            for(Node x = head; x != null;x = head.next){
                if(obj.equals(x.data)){
                    //获取头节点的后一个节点
                    Node next = x.next;
                    //将头节点的后一个节点的前一个节点指向null
                    next.prev = null;
                    //将头节点的后一个节点指向头节点
                    head = next;
                    size--;
                    return;
                }
            }
        }

    }

    //获取指定位置的元素
    public Object get(int index){
        return null;
    }

    //获取链表的长度
    public int size(){
        return size;
    }

    //获取链表的头节点元素
    public Object getFirst(){
        return head.data;
    }

    //获取链表的尾节点元素
    public Object getLast(){
        return last.data;
    }


    //遍历链表
    public void iterator(){
        //判断链表是否为空
        if(head == null){
            throw new RuntimeException("链表为空");
        }
        //从头节点开始遍历
        Node temp = head;
        while (temp != null){
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    //获取指定位置的节点
    private Node node(int index) {
        if(index < (size >> 1)){
            Node temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
            return temp;
        }else {
            Node temp = last;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
            return temp;
        }
    }

    public static void main(String[] args) {
        MyLinkList myLinkList = new MyLinkList();
        myLinkList.add("a");
        myLinkList.add("b");
        myLinkList.add("c");
        myLinkList.add("d");
        myLinkList.add(1,"e");
        myLinkList.remove("a");
        myLinkList.iterator();
    }

}
