package com.phuongtd.blog.services;

import com.phuongtd.blog.entities.User;
import com.phuongtd.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
