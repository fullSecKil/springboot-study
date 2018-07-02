package com.tl.service;

import com.tl.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    /**
     *getUser
     *
     * @param age
     * @return  List<User>
     */
    public List<User> getUser(String age);
}
