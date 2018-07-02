package com.example.repeat_submission.interceptor;

import org.aspectj.lang.ProceedingJoinPoint;

/**
 *  key生成器
 *
 * @author Dusk
 * @date 2018/06/28
 */
public interface CacheKeyGenerator {
    /**
     *  获取AOP参数，生成指定缓存Key
     *
     * @param pjp
     * @return 缓存KEY
     */
    String getLockKey(ProceedingJoinPoint pjp);
}
