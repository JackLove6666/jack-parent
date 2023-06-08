package com.cloud.jack.app.test.spring.ioc.four;

import lombok.Data;


public interface BeanA {


    default void methodA1(){
        System.out.println("methodA1");
    }

    default void methodA2(){
        System.out.println("methodA2");
    }

}
