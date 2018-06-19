package com.example.config;

import com.example.exception.CustomException;
import com.example.exception.ErrorResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.BindException;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(CustomException.class)
    //自定义异常处理程序
    public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response ){
        response.setStatus(HttpStatus.BAD_REQUEST.value());     //设置状态
        CustomException exception = (CustomException) e;        //客户异常
        return new ErrorResponseEntity(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        System.out.println(e.toString());
        RuntimeException exception = (RuntimeException) e;      //运行时异常
        return new ErrorResponseEntity(500,exception.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        //方法论证
        System.out.println(ex.toString());
        if(ex instanceof MethodArgumentTypeMismatchException){
            //方法参数类型不匹配异常
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：" + exception.getParameter().getMethod().getName() + "，参数：" + exception.getName()
                    + ",信息：" + exception.getLocalizedMessage());

            return new ResponseEntity<>(new ErrorResponseEntity(status.value(), exception.getMessage()), status);   //"参数转换失败"
        }
        return new ResponseEntity<>(new ErrorResponseEntity(status.value(), ex.getMessage()), status);
    }
}
