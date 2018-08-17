package com.example.userinformanage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 *  密码编码器
 * @author Dusk
 */
@Configuration
public class PasswordEncoderConfig {
    /**
     *  密码加密
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
