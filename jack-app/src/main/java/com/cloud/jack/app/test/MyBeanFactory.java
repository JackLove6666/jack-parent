package com.cloud.jack.app.test;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.FactoryBean;

/**
 * 1.动态生成代理对象
 */
public class MyBeanFactory implements FactoryBean<T> {


    @Override
    public T getObject() throws Exception {
        //自定义对象的初始化过程
        return new T();
    }

    @Override
    public Class<?> getObjectType() {
        return T.class;
    }
}
