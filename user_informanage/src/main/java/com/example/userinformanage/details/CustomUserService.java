package com.example.userinformanage.details;

import com.example.userinformanage.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Dusk
 */
@Service
public class CustomUserService implements UserDetailsService {

    @Autowired
    UserService userService;

    final static private Logger log = LoggerFactory.getLogger(CustomUserService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("用户名： {}", username);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        com.example.userinformanage.pojo.User loginuser = userService.getUser(username);

        // 得到loginuser的权限用，分隔
        List<GrantedAuthority> grantedAuthorities = Arrays.stream(loginuser.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());

        System.out.println(grantedAuthorities);

     /*User user = new User(username, "123456",
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));*/
     //User(username,"123456",authorities);
        return new User(loginuser.getUsername(), loginuser.getPassword(), grantedAuthorities);
    }
}

