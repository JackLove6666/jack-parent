package com.cloud.jack.app.test.collection;

public class MyArrayList {

    private Object[] elementData;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }


    public void add(Object obj){
        elementData[size++] = obj;
    }

    //指定位置添加
    public void add(int index, Object obj){
        if (index < 0 || index > size){
            throw new RuntimeException("索引不合法");
        }
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = obj;
        size++;
    }

    public boolean remove(Object obj){
        //判断是否为空
        if(obj == null){
            for(int i= 0;i < size;i++){
                if(elementData[i] == null){
                    for (int j = i; j < size - 1; j++) {
                        //将后面的元素往前移动
                        elementData[j] = elementData[j + 1];
                    }
                    //将最后一个元素置为null
                    elementData[--size] = null;
                    return true;
                }
            }
        }
        for (int i = 0; i < size; i++) {
            if (elementData[i].equals(obj)){
                for (int j = i; j < size - 1; j++) {
                    elementData[j] = elementData[j + 1];
                }
                elementData[--size] = null;
                return true;
            }
        }
        return false;
    }

    public Object get(int index){
        return elementData[index];
    }

    public int size(){
        return size;
    }

    //遍历
    public void iterator(){
        for (int i = 0; i < elementData.length; i++) {
            System.out.println(elementData[i]);
        }
    }


    public static void main(String[] args) {
        MyArrayList myArrayList = new MyArrayList();
        myArrayList.add("a");
        myArrayList.add("b");
        myArrayList.add(1,"c");
        boolean d = myArrayList.remove("d");
        System.out.println("删除"+d);
        myArrayList.iterator();
    }

}
