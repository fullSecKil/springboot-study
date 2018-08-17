package com.example.userinformanage.config;

import com.example.userinformanage.details.CustomUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // "/","/loginform","/register","/assets/**"所有请求均可访问
                .antMatchers("/login","/register","/assets/**")  // 登录页面可以任意访问
                .permitAll()
                .antMatchers(HttpMethod.POST, "/registerPut", "/checkName")
                .permitAll()
                .antMatchers("/a").access("hasRole('ROLE_ADMIN') and principal.username == 'abc'")
                .anyRequest().authenticated().and()
                .formLogin().loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/loginform")
                .failureUrl("/login?error")
                .permitAll()
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .permitAll()        // 注销行为任意访问
                .and().rememberMe().tokenValiditySeconds(2419200)
                ;
    }

    @Autowired
    CustomUserService customUserService;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      /*  auth.userDetailsService(customUserService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return (String)charSequence;    //MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                System.out.println(s + "---" + (String)charSequence);
                return charSequence.equals((String) s);
            }
        });*/
       auth.userDetailsService(customUserService).passwordEncoder(passwordEncoder);
    }
}
