package com.example.boot_constrain.annotation;

import com.example.boot_constrain.validator.DateTimeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD,PARAMETER})              //目标
@Retention(RUNTIME)//保留
@Constraint(validatedBy = DateTimeValidator.class)      //约束
public @interface DateTime {
    String message() default "格式错误";
    String format() default "yyyy-MM-dd";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
