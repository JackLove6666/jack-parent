package com.cloud.jack.app.test.spring.ioc.two;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * 实例化增强：使用 InstantiationAwareBeanPostProcessor 接口在 Bean 实例化之前和之后对 Bean 实例进行相应的定制化操作，如动态代理等。
 */
@Component
public class CustomizedInstantiationAwareBeanPostProcessor  implements InstantiationAwareBeanPostProcessor {


    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        // BeanA 类型的 Bean 在实例化之前进行动态代理
        BeanA beanA = new BeanAImpl();
        if(beanClass.isAssignableFrom(BeanA.class)){
            BeanA finalBeanA = beanA;
            beanA = (BeanA) Proxy.newProxyInstance(beanA.getClass().getClassLoader(), beanA.getClass().getInterfaces(),
                    ((proxy, method, args) -> {
                        // 代理方法逻辑
                       if(method.getName().equals("methodA1")){
                           // 这里可以添加需要的逻辑处理
                           System.out.println("Before Method A1");
                           Object result = method.invoke(finalBeanA, args);
                           System.out.println("After Method A1");
                           return result;
                       }
                       return method.invoke(finalBeanA,args);
                    }));
            return finalBeanA;
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (bean instanceof BeanB) {
            // BeanB 类型的 Bean 在实例化之后进行动态代理
            BeanB beanB = (BeanB) bean;
            BeanB finalBeanB = beanB;
            beanB = (BeanB) Proxy.newProxyInstance(beanB.getClass().getClassLoader(),
                    beanB.getClass().getInterfaces(),
                    (proxy, method, args) -> {
                        // 代理方法逻辑
                        if (method.getName().equals("methodB1")) {
                            // 这里可以添加需要的逻辑处理
                            System.out.println("Before Method B1");
                            Object result = method.invoke(finalBeanB, args);
                            System.out.println("After Method B1");
                            return result;
                        }
                        return method.invoke(finalBeanB, args);
                    });
            return true;
        }
        return true;
    }
}
