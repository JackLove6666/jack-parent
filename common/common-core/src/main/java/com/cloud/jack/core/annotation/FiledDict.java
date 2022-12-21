package com.cloud.jack.core.annotation;

import java.lang.annotation.*;

/**
 * 字典注解
 *
 * @author ML
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledDict {

    /**
     * 字典类型
     *
     * @return
     */
    String value();

}
