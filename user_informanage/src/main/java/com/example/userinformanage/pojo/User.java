package com.example.userinformanage.pojo;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 *  User实体类
 *
 *  @author Dusk
 */
@Data
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "用户名不能为空")
    @Length(min=2, max = 10, message = "用户名长度必须在{min}-{max}之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Length(min=6, max = 15, message = "密码长度必须在{min}-{max}之间")
    private String password;

    private String realname;

    @Email
    private String email;

    private String photo;

    private String title;

    private String roles = "ROLE_USER";

    private Integer isAction = 1;

    public User(){};

    public User(String username, String password, String email, String realname, String photo){
        this.username = username;
        this.password = password;
        this.email = email;
        this.realname = realname;
        this.photo = photo;
    }

}
