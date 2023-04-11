package com.cloud.jack.app.test;

import cn.hutool.core.collection.CollUtil;
import org.redisson.RedissonLock;

import java.util.ArrayList;
import java.util.List;

public class Test {


    public static void main(String[] args) {

            //写一个流量请求访问限制
        List<Integer> a = new ArrayList<>();
        if(CollUtil.isNotEmpty(a)){
            System.out.println(a);
        }else {
            System.out.println("kong");
        }


    }
    //写一个手机号校验方法需要正则表达式
    public static boolean isMobile(String mobile) {
        String regExp = "^((13[0-9])|(14[5,7])|(15([0-3]|[5-9]))|(16[6])|(17[0-8])|(18[0-9])|(19[8-9]))\\d{8}$";
        return mobile.matches(regExp);
    }

    //写一个邮箱校验方法
    public static boolean isEmail(String email) {
        String regExp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(regExp);
    }

    //写一个分布式锁工具方法
    public static void lock(){
        //1.使用redis Redission
        //2.使用zookeeper
        //3.使用数据库
    }
    //写一个登录方法
    public static void login(String username,String password){
        //1.判断是否登录

        //2.判断是否有权限
        //3.判断是否有token
    }

}
