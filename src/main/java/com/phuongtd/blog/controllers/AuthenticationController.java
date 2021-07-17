package com.phuongtd.blog.controllers;

import com.phuongtd.blog.entities.Login;
import com.phuongtd.blog.entities.User;
import com.phuongtd.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public User login(@RequestBody Login login) {
        return userService.login(login);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return  userService.register(user);
    }
}
