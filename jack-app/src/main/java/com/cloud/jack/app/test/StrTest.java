package com.cloud.jack.app.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StrTest {


    public static void main(String[] args) {
        String str = "B09MTSS446-N";
        System.out.println(str.lastIndexOf("a"));
        System.out.println(str.lastIndexOf("N"));
        //判断字符串是否以某个字符串结尾
        System.out.println(str.endsWith("-N"));
        System.out.println(str.endsWith("c"));
        // 字符串后缀排除 7N 正则表达式
        System.out.println(str.replaceAll("[7N]$", ""));

        // 增强for循环 删除元素
        List<String> list = new ArrayList();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("3");
        list.add("5");

        outer:
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < i-1; j++) {
                System.out.println("jjjj="+j);
                if(j == 0){
                    continue outer;
                }
            }
        }

        for (String s : list) {
            if(s.equals("2")){
                list.remove(s);
            }
        }
        System.out.println(list);

//        Iterator<String> iterator = list.iterator();
//        while(iterator.hasNext()){
//            if(iterator.next().equals("2")){
//                iterator.remove();
//            }
//
//        }
//        System.out.println(list);






    }
}
