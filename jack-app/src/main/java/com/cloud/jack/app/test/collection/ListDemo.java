package com.cloud.jack.app.test.collection;

import com.cloud.jack.app.entity.wx.User;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

@Slf4j
public class ListDemo {


    //定义一个支持原子性操作的Integer类型的变量
    private static volatile Integer count = 0;


    public static void main(String[] args) {
        //创建一个list集合
        List<String> list = new ArrayList<>();
        if(list.size() == 0){
              log.info("list集合为空");
        }
        if(list.isEmpty()){
            log.info("list集合为空");
        }
        list.add(null);
        boolean contains = list.contains(null);
        log.info("list集合是否包含null:{}",contains);
        //添加元素
        list.add("1");
        list.add("2");
        list.add("3");
        if(list.contains("1")){
            log.info("list集合包含1");
        }
        //定义一个用户集合
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("jack");
        user.setUserid("1");

        User user1 = new User();
        user1.setName("jack1");
        user1.setUserid("2");
        userList.add(user);
        userList.add(user1);

        User user3 = new User();
        user3.setName("jack2");
        user3.setUserid("3");
        userList.add(user3);
        //普通for循环遍历
        for (int i = 0; i < userList.size(); i++) {
            User user2 = userList.get(i);
            if(user2.getName().equals("jack")){
                userList.remove(user2);
            }
            log.info("普通for循环遍历:{}",user2);
        }
        //增强for循环遍历 底层使用的是迭代器
//        for (User user2 : userList) {
//            if(user2.getName().equals("jack")){
//                userList.remove(user2);
//            }
//            log.info("增强for循环遍历:{}",user2);
//        }
//        log.info("增强for循环遍历后的userList:{}",userList);
        //迭代器遍历 适合删除元素
        Iterator<User> userIterator = userList.iterator();
        while(userIterator.hasNext()){
            User next = userIterator.next();
            if(next.getName().equals("jack")){
                userIterator.remove();
            }
            log.info("迭代器遍历:{}",next);
        }
        log.info("迭代器遍历后的userList:{}",userList);
        // 转换为数组
        Object[] objects = userList.toArray();
        //定义一个新的数组
        Object[] objects1 = new Object[objects.length];
        //将原数组的元素复制到新数组中
        //参数1:原数组  参数2:原数组的起始位置  参数3:新数组  参数4:新数组的起始位置  参数5:复制的长度
        System.arraycopy(objects,0,objects1,0,objects.length);
        log.info("转换为数组:{}",objects1);
        //list 排序本质上是数组排序 通过数组的排序方法实现
        userList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        log.info("排序后的userList:{}",userList);
        //list 清除元素
        userList.clear();
        log.info("清除元素后的userList:{}",userList);


    }
}
