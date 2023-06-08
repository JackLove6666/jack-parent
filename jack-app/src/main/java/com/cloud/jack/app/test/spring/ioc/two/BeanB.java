package com.cloud.jack.app.test.spring.ioc.two;

public class BeanB {

    static {
        System.out.println("Hello BeanB");
    }

    public void methodB1(){
        System.out.println("methodB1");
    }
}
