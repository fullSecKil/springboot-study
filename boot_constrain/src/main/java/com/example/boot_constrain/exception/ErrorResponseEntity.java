package com.example.boot_constrain.exception;

import lombok.Data;

/**
 * 返回的异常信息的格式
 */

@Data
public class ErrorResponseEntity {
    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message){
        this.code = code;
        this.message = message;
    };
}
