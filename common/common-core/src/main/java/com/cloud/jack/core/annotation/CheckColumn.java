package com.cloud.jack.core.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value = ElementType.FIELD)
public @interface CheckColumn {

    boolean required() default true;

    String columnName() default "";

    int maxLength() default 0;

    boolean isNumeric() default false;

    boolean isNumber() default  false;

    int maxLimit() default -1;

    int minLimit() default -1;

    int maxKeepDigit() default -1;

    int amountLimit() default -1;




}
