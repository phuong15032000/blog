package com.trandiepphuong.blog.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.trandiepphuong.blog.entities.User;
import com.trandiepphuong.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping
    @JsonIgnoreProperties("password")
    public User getUserByEmail(@RequestParam String email) {
        return userRepository.findByEmail(email);
    }
}
