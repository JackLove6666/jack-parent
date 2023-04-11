package com.cloud.jack.app.test.collection;

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
            HashMapDemo hashMapDemo = new HashMapDemo();
            hashMapDemo.put(new Node());
            hashMapDemo.put(new Node());
            System.out.println(hashMapDemo.get(1));
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
