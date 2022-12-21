package com.cloud.jack.core.annotation;

import java.lang.annotation.*;

/**
 * 缓存注解
 *
 * @author LEO
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledCache {

    /**
     * 缓存代理对象
     *
     * @return
     */
    String prefix();

    /**
     * 缓存代理主键
     *
     * @return
     */
    String filed() default "id";

    /**
     * 缓存代理返回值
     *
     * @return
     */
    String[] value() default {"name"};

}
