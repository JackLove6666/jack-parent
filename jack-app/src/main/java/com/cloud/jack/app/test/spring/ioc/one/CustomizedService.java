package com.cloud.jack.app.test.spring.ioc.one;

import org.springframework.stereotype.Component;

@Component
public class CustomizedService {



    public void doSomething(){
        BeanA beanA = (BeanA) CacheUtils.get("beanA");
        BeanB beanB = (BeanB) CacheUtils.get("beanB");

        // 使用该 Bean 实例调用相关方法
        beanA.methodA1();
        beanB.methodB1();
    }
}
