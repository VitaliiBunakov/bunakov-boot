package com.bunakov.service;

import com.bunakov.model.User;

import java.util.List;

public interface UserService {
    void save (User user) ;
    List<User> findAll();
    User findByUserName(String userName);
    boolean existsByUserName (String username);

}

