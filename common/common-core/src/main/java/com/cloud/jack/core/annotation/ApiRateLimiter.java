package com.cloud.jack.core.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ApiRateLimiter {

    /**
     * api key
     * @return
     */
    String confKey();

    /**
     * api value
     * @return
     */
    String value();

    /**
     * 访问api最大线程数,默认是2
     * @return
     */
    int maxThreadCount() default 2;

    /**
     * 未获取到资源的最大等待时间，默认时间为2s
     * @return
     */
    int maxWaitTime() default 2;



}
