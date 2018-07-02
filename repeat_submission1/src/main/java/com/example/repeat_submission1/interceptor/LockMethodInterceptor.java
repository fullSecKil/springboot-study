package com.example.repeat_submission1.interceptor;

import com.example.repeat_submission1.annotation.LocalLock;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

@Aspect
@Configuration
public class LockMethodInterceptor {
    private static final Cache<String, Object> CACHES = CacheBuilder.newBuilder()
            // 最大缓存100个
            .maximumSize(1000)
            // 设置缓存5秒钟后过期
            .expireAfterWrite(5, TimeUnit.SECONDS)
            .build();
    //execution匹配连接点的执行方法
    @Pointcut("execution(public * *(..)) && @annotation(com.example.repeat_submission1.annotation.LocalLock)")
    public void localLock(){}

    /**
     * 在具体的 interceptor() 方法上采用的是 Around（环绕增强） ，所有带 LocalLock 注解的都将被切面处理
     *
     * @param pjp
     * @return
     */
    //环绕通知方法
    @Around("localLock()")
    public Object interceptor(ProceedingJoinPoint pjp){
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();

        LocalLock localLock = method.getAnnotation(LocalLock.class);
        String key = getKey(localLock.key(), pjp.getArgs());
        if(!StringUtils.isEmpty(key)){
            if(CACHES.getIfPresent(key) != null){
                throw new RuntimeException("请勿重复请求");
            }
            // 如果第一次请求，就将key当前对象压入缓存中
            CACHES.put(key, key);
        }
        try{
            return pjp.proceed();
        }catch (Throwable throwable){
            throw new RuntimeException("服务器异常");
        }
    }

    /**
     *  key  的生成策略，如果想灵活可以写成接口与实现类的方式
     *
     * @param keyExpress
     * @param args
     * @return
     */
    private String getKey(String keyExpress, Object[] args){
        for(int i=0; i<args.length; i++){
            keyExpress = keyExpress.replace("arg[" + i + "]", args[i].toString());
        }
        return keyExpress;
    }

}
