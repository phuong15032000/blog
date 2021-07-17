package com.phuongtd.blog.controller;

import com.phuongtd.blog.entities.User;
import com.phuongtd.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/{email}")
    public User findByEmail(@PathVariable String email){
        return userService.findByEmail(email);
    }
}
