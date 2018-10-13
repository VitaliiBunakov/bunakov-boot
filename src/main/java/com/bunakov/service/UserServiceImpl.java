package com.bunakov.service;

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
    public void save(User user) {
        userRepository.save(user);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}


