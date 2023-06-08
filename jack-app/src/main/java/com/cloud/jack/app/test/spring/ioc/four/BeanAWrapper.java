package com.cloud.jack.app.test.spring.ioc.four;

public class BeanAWrapper implements BeanA{

    private BeanA beanA;

    public BeanAWrapper(BeanA beanA) {
        this.beanA = beanA;
    }

    @Override
    public void methodA1() {
        // 在原方法执行前后添加处理逻辑
        System.out.println("Before Method A1");
        beanA.methodA1();
        System.out.println("After Method A1");
    }

    @Override
    public void methodA2() {
        beanA.methodA2();
    }


}
