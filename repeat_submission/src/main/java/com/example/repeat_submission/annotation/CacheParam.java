package com.example.repeat_submission.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited

public @interface CacheParam {
    /**
     * 字段名
     *
     * @return String
     */
    String name() default "";
}
