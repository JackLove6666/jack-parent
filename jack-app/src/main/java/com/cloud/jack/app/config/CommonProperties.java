package com.cloud.jack.app.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Data
@ConfigurationProperties(prefix = "app.common")
public class CommonProperties {


    private String hostPath;


    /**
     * 通过@PostConstruct来标记这个init方法为bean的初始化方法
     **/
    @PostConstruct
    private void init() {
        //这条语句会在 beanprocessor的postProcessBeforeInitialization方法执行之后执行 2
        System.out.println("执行初始化方法");
    }
}
