package com.trandiepphuong.blog.controllers;

import com.trandiepphuong.blog.entities.Login;
import com.trandiepphuong.blog.entities.User;
import com.trandiepphuong.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        return userService.login(login);
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return  userService.register(user);
    }
}
