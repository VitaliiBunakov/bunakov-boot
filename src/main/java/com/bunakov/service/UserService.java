package com.bunakov.service;

import com.bunakov.exeptions.UserAlreadyExistsExeption;
import com.bunakov.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user) throws UserAlreadyExistsExeption;
    List<User> findAll();
    boolean existsByUserName (String username);

}

