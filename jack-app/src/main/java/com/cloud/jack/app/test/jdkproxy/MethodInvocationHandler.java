package com.cloud.jack.app.test.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MethodInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("aop start");
        Object invoke = method.invoke(new HelloWordServiceImpl(), args);
        System.out.println("result"+invoke);
        System.out.println("aop end");
        return invoke;
    }
}
