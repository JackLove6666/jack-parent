package com.cloud.jack.app.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    interface Foo{
        void foo();
    }

    static class Target implements Foo{

        @Override
        public void foo() {
            System.out.println("target foo");
        }
    }


    public static void main(String[] args) {

        Target target = new Target();
        Foo newProxyInstance = (Foo)Proxy.newProxyInstance(JdkProxyDemo.class.getClassLoader(), new Class[]{Foo.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object invoke = method.invoke(target);
                System.out.println("after");
                return invoke;
            }
        });

        newProxyInstance.foo();


    }
}
