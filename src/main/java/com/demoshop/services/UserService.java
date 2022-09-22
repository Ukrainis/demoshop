package com.demoshop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoshop.entities.User;
import com.demoshop.interfaces.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(User user) {
        User createdUser = userRepository.save(user);

        return createdUser;
    }
}
