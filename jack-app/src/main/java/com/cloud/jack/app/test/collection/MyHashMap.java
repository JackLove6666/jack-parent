package com.cloud.jack.app.test.collection;

public class MyHashMap {


    //定义一个数组，用来存放数据
    private Object[] table;

    //定义一个变量，用来存放数组的长度
    private int size;

    //定义一个Node类，用来存放数据
    static class Node{
        int hash;
        Object key;
        Object value;
        Node next;
    }

    //定义添加数据的方法
    public Object put(Object key, Object value){
        table = new Object[16];
        table[6] = value;
        return value;
    }

    //定义一个方法，用来获取hash值
    public int hash(String key){
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

}
