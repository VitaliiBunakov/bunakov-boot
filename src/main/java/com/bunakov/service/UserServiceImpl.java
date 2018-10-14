package com.bunakov.service;

import com.bunakov.exeptions.UserAlreadyExistsExeption;
import com.bunakov.model.User;
import com.bunakov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;


    @Override
    public boolean existsByUserName(String username) {
        return userRepository.existsByUserName(username);
    }


    @Override
    public User createUser(User user) throws UserAlreadyExistsExeption {
        if (!userRepository.existsByUserName(user.getUserName())) {
            userRepository.save(user);
            return user;
        } else {
            throw new UserAlreadyExistsExeption(user.getUserName());
        }
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}


