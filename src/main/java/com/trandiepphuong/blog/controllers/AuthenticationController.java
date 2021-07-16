package com.trandiepphuong.blog.controllers;

import com.trandiepphuong.blog.configurations.TokenProvider;
import com.trandiepphuong.blog.entities.AuthToken;
import com.trandiepphuong.blog.entities.Login;
import com.trandiepphuong.blog.entities.Role;
import com.trandiepphuong.blog.entities.User;
import com.trandiepphuong.blog.repositories.RoleRepository;
import com.trandiepphuong.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.findByEmail(user.getEmail()) == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRoleList(new ArrayList<>());
            user.getRoleList().add(roleRepository.findByName("ROLE_MEMBER"));
            userRepository.save(user);
            return ResponseEntity.ok(user);
        }
        return new ResponseEntity<>("Ten dang nhap da ton tai", HttpStatus.NOT_FOUND);
    }
}
