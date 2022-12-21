package com.cloud.jack.core.annotation;

import java.lang.annotation.*;

/**
 * 返回类型切面注解，用于增强字段类型
 *
 * @author ML
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledType {

    /**
     * 字典类型
     *
     * @return
     */
    String value();

}
