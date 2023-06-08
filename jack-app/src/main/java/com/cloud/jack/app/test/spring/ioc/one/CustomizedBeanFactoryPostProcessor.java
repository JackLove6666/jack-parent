package com.cloud.jack.app.test.spring.ioc.one;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanFactoryPostProcessor：在容器创建前获取所有 Bean 的定义信息
 * 1.使用这些信息将需要缓存的 Bean 实例提前创建并缓存到内存中，从而提高应用的响应速度。
 */
//@Component
public class CustomizedBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

        // 获取需要缓存的 Bean 类型
        List<Class<?>> cacheClazzList = new ArrayList<>();
        cacheClazzList.add(BeanA.class);
        cacheClazzList.add(BeanB.class);

        // 缓存需要缓存的 Bean 实例
        for (Class<?> clazz : cacheClazzList) {
            BeanDefinition beanDefinition = new RootBeanDefinition(clazz);
            String beanName = StringUtils.uncapitalize(clazz.getSimpleName());
//            beanFactory.registerBeanDefinition(beanName, beanDefinition);版本问题

            Object beanInstance = beanFactory.getBean(beanName);
            CacheUtils.put(beanName, beanInstance); // 将 Bean 实例放入缓存中
        }

    }
}
