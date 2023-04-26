package com.cloud.jack.app.test.collection;

import java.util.HashMap;

public class HashMapDemo {

        static class Node{
            int hash;
            Object key;
            Object value;
            Node next;
        }

        public static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

        public static final int MAXIMUM_CAPACITY = 1 << 30;

        public static final float DEFAULT_LOAD_FACTOR = 0.75f;

        public static final int TREEIFY_THRESHOLD = 8;

        public static final int UNTREEIFY_THRESHOLD = 6;

        public static final int MIN_TREEIFY_CAPACITY = 64;

        Object[] table;

        int size;

        int threshold;

        float loadFactor;

        public HashMapDemo() {
            this.loadFactor = DEFAULT_LOAD_FACTOR;
        }

        public HashMapDemo(int initialCapacity) {
            this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }

        public HashMapDemo(int initialCapacity, float loadFactor) {
            if (initialCapacity < 0)
                throw new IllegalArgumentException("Illegal initial capacity: " +
                                                   initialCapacity);
            if (initialCapacity > MAXIMUM_CAPACITY)
                initialCapacity = MAXIMUM_CAPACITY;
            if (loadFactor <= 0 || Float.isNaN(loadFactor))
                throw new IllegalArgumentException("Illegal load factor: " +
                                                   loadFactor);
            this.loadFactor = loadFactor;
            this.threshold = tableSizeFor(initialCapacity);
        }

        public int hash(String key) {
            int h;
            return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
        }

        public int hashCode(String key){
            return  key.hashCode();
        }

        static final int tableSizeFor(int cap) {
            int n = cap - 1;
            n |= n >>> 1;
            n |= n >>> 2;
            n |= n >>> 4;
            n |= n >>> 8;
            n |= n >>> 16;
            return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
        }

        public static void main(String[] args) {
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                if(i == 0)
                break;
                System.out.println("会执行吗？");
            }
            HashMapDemo hashMapDemo = new HashMapDemo();

            int hash = hashMapDemo.hash("捷克梗");
            int hashCode = hashMapDemo.hashCode("捷克梗");
            System.out.println(hash);
            System.out.println(hashCode);
            hashMapDemo.put(new Node());
            hashMapDemo.put(new Node());
//            System.out.println(hashMapDemo.get(1));
            HashMap hashMap = new HashMap();
            Object put = hashMap.put("1", "1");
            Object putIfAbsent = hashMap.putIfAbsent("1", "2");
            System.out.println(put);
            System.out.println(putIfAbsent);
            //体验一下HashMap的右移运算
            int i = 16;
            System.out.println(i >>> 1);
            System.out.println(i >>> 2);
            //扰动函数的作用是为了让高位的hash值参与运算，减少碰撞,为什么要异或运算呢？
            //异或运算的特点是：相同为0，不同为1，所以相同的hash值经过异或运算后，高位的hash值参与运算，减少碰撞
            //为什么要右移运算呢？
            //右移运算的特点是：低位溢出，高位补0，所以高位的hash值参与运算，减少碰撞
            //为什么要这样做呢？
            //因为HashMap的数组长度是2的幂次方，所以hash值与数组长度-1做与运算，可以保证hash值落在数组的范围内

            System.out.println(hashCode ^ (hashCode >>> 16));

        }


        //写一个HashMap增删改查的方法
        public void put(Node node){
            table = new Object[DEFAULT_INITIAL_CAPACITY];
            int i = node.hash & (table.length - 1);
            if (table[i] == null) {
                table[i] = node;
            } else {
                Node e = (Node) table[i];
                if (e.hash == node.hash &&
                    ((e.key == node.key) || (e.key != null && e.key.equals(node.key)))) {
                    e.value = node.value;
                } else {
                    while (e.next != null) {
                        e = e.next;
                    }
                    e.next = node;
                }
            }
        }

        public Object get(int hash){
            int i = hash & (table.length - 1);
            Node e = (Node) table[i];
            if (e.hash == hash) {
                return e.value;
            } else {
                while (e.next != null) {
                    e = e.next;
                    if (e.hash == hash) {
                        return e.value;
                    }
                }
            }
            return null;
        }





}
