package com.cloud.jack.app.test.spring.ioc.five;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CustomizedListener implements InitializingBean, DisposableBean {


    @Override
    public void destroy() throws Exception {
        System.out.println("CustomizedListener: Container is destroyed");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("CustomizedListener: Container is initialized");
    }
}
