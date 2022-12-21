package com.cloud.jack.core.annotation;

import java.lang.annotation.*;

/**
 * 枚举注解
 *
 * @author LEO
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledEmun {

    /**
     * 枚举类
     *
     * @return
     */
    Class<?> value() default Object.class;

}
