package com.cloud.jack.app.test.jdkproxy;

import java.lang.reflect.Proxy;

public class JdkProxyTest {


    public static void main(String[] args) {
        HelloWorldService helloWordService = new HelloWordServiceImpl();
        HelloWorldService newProxyInstance = (HelloWorldService)Proxy.newProxyInstance(helloWordService.getClass().getClassLoader(), new Class[]{HelloWorldService.class}, new MethodInvocationHandler());

        newProxyInstance.hello("捷克梗");

    }
}
