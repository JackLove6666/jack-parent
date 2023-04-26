package com.cloud.jack.app.test.collection;

import com.cloud.jack.app.entity.wx.User;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.LinkedList;
@Slf4j
public class LinkListDemo {


    public static void main(String[] args) {
        //创建一个链表
        LinkedList<String> linkedList = new LinkedList<>();
        //添加元素
        linkedList.add("1");
        linkedList.add("2");
        linkedList.add("3");
        linkedList.add("4");
        //获取链表的第一个元素
        String first = linkedList.getFirst();
        log.info("链表的第一个元素:{}",first);
        //获取链表的最后一个元素
        String last = linkedList.getLast();
        log.info("链表的最后一个元素:{}",last);
        //获取链表的第一个元素并删除
/*        String removeFirst = linkedList.removeFirst();
        log.info("链表的第一个元素并删除:{}",removeFirst);*/
        //获取链表的最后一个元素并删除
/*        String removeLast = linkedList.removeLast();
        log.info("链表的最后一个元素并删除:{}",removeLast);*/
        //遍历链表
        for (String s : linkedList) {
            if(s.equals("2")){
                linkedList.remove(s);
            }
            log.info("遍历链表:{}",s);
        }
        log.info("遍历链表:{}",linkedList);

        //创建一个链表用户集合
        LinkedList<User> userList = new LinkedList<>();
        User user = new User();
        user.setName("jack");
        user.setUserid("1");
        userList.add(user);
        User user1 = new User();
        user1.setName("jack1");
        user1.setUserid("2");
        userList.add(user1);
        User user2 = new User();
        user2.setName("jack2");
        user2.setUserid("3");
        userList.add(1,user2);
        //普通for循环遍历
        for (int i = 0; i < userList.size(); i++) {
            User user3 = userList.get(i);
            log.info("普通for循环遍历:{}",user3);
        }
        //遍历链表
//        for (User user3 : userList) {
//            if(user3.getName().equals("jack")){
//                userList.remove(user3);
//            }
//            log.info("遍历链表:{}",user3);
//        }
        //迭代器遍历链表
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()){
            User next = iterator.next();
            if(next.getName().equals("jack")){
                iterator.remove();
            }
            log.info("迭代器遍历链表:{}",next);
        }
        log.info("遍历链表:{}",userList);

        User first1 = userList.getFirst();
        log.info("链表的第一个元素:{}",first1);
    }


}
