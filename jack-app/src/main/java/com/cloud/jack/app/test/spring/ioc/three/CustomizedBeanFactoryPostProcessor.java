package com.cloud.jack.app.test.spring.ioc.three;


import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 3.依赖注入：使用 BeanFactoryPostProcessor 接口在容器创建前获取所有 Bean 的定义信息，根据依赖关系对 Bean 的创建和初始化顺序进行调整。
 */
//@Component
public class CustomizedBeanFactoryPostProcessor implements BeanFactoryPostProcessor {


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanA = beanFactory.getBeanDefinition("beanA");
        BeanDefinition beanB = beanFactory.getBeanDefinition("beanB");
        beanA.setDependsOn(new String[]{"beanB"});
        beanB.setDependsOn(new String[]{"beanB"});
        beanB.setAutowireCandidate(true);
    }
}
