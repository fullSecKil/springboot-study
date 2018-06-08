package com.example.swaggertest.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
@Getter
@Setter
@ToString
@ApiModel
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    public User(){}

    public User(Long id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
