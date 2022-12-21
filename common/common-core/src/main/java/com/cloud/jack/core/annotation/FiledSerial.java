package com.cloud.jack.core.annotation;

import java.lang.annotation.*;

/**
 * 流水号注解
 *
 * @author LEO
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface FiledSerial {

    /**
     * 流水号规则
     * @return
     */
    String value();

}
