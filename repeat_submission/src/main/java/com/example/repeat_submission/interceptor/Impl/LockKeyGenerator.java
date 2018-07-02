package com.example.repeat_submission.interceptor.Impl;

import com.example.repeat_submission.annotation.CacheLock;
import com.example.repeat_submission.annotation.CacheParam;
import com.example.repeat_submission.interceptor.CacheKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 *  通过接口注入的防止去写不同的生成规则
 *
 * @author Dusk
 * @since
 */

public class LockKeyGenerator implements CacheKeyGenerator {

    @Override
    public String getLockKey(ProceedingJoinPoint pjp) {   //继续加入点
        // 模型签名
        MethodSignature signature = (MethodSignature)pjp.getSignature();
        Method method = signature.getMethod();
        // 模型里得到注解
        CacheLock lockAnnotation = method.getAnnotation(CacheLock.class);

        // 参数
        final Object[] args = pjp.getArgs();
        // 参数
        final Parameter[] parameters = method.getParameters();
        StringBuilder builder = new StringBuilder();

        // TODO 默认方法里自带 CacheParam 注解的属性,如果没有尝试着解析实体对象中的
        for (int i = 0; i < parameters.length; i++){
            final CacheParam annotation = parameters[i].getAnnotation(CacheParam.class);
            if(annotation == null){
                continue;
            }
            builder.append(lockAnnotation.delimiter()).append(args[i]);
        }

        if(StringUtils.isEmpty(builder.toString())){
            // 得到参数的注解
            final Annotation[][] parameterAnnotations = method.getParameterAnnotations();
            for (int i = 0; i < parameterAnnotations.length; i++ ){
                final Object object = args[i];
                final Field[] fields = object.getClass().getDeclaredFields();
                for (Field field : fields){
                    final CacheParam annotation = field.getAnnotation(CacheParam.class);
                    if (annotation == null){
                        continue;
                    }
                    field.setAccessible(true);
                    builder.append(lockAnnotation.delimiter()).append(ReflectionUtils.getField(field, object));
                }
            }
        }
        return lockAnnotation.prefix() + builder.toString();
    }
}
