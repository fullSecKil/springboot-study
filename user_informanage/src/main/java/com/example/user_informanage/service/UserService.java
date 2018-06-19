package com.example.user_informanage.service;

import com.example.user_informanage.pojo.User;

public interface UserService {
    public User getUser(String username);

    public boolean addUser(User user);
}
