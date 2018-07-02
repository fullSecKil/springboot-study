package com.example.repeat_submission1.annotation;

import java.lang.annotation.*;

/**
 * 定义锁的注解
 *
 * @author Dusk
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
    //本地锁
public @interface LocalLock {
    /**
     * @author fly
     * @return
     */
    String key() default "";

    /**
     * 过期时间 TODO 由于用的 guava 暂时就忽略这属性吧 集成 redis 需要用到
     * @author fly
     *
     * @return
     */
    int expire() default 5;
}
