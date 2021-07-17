package com.phuongtd.blog.services;

import com.phuongtd.blog.configurations.TokenProvider;
import com.phuongtd.blog.entities.AuthToken;
import com.phuongtd.blog.entities.Login;
import com.phuongtd.blog.entities.User;
import com.phuongtd.blog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenProvider jwtTokenUtil;
    @Autowired
    private RoleService roleService;

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public ResponseEntity<?> login(Login login) {
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

    public String register(User user) {
        if (this.findByEmail(user.getEmail()) == null) {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRoleList(new ArrayList<>());
            user.getRoleList().add(roleService.findByName("ROLE_MEMBER"));
            this.save(user);
            return "Register successful!";
        }
        return ("Email has already been used! Please register again!");
    }
}
