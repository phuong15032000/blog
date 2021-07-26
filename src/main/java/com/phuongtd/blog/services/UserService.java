package com.phuongtd.blog.services;

import com.phuongtd.blog.configurations.TokenProvider;
import com.phuongtd.blog.entities.AuthToken;
import com.phuongtd.blog.entities.Login;
import com.phuongtd.blog.entities.Role;
import com.phuongtd.blog.entities.User;
import com.phuongtd.blog.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<User> login(Login login) throws Exception {
        User user = userRepository.findByEmail(login.getUsername());
        if (user.isActive()) {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login.getUsername(),
                            login.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = jwtTokenUtil.generateToken(authentication);
//        User user = userRepository.findByEmail(login.getUsername());
            user.setToken(token);
            return new ResponseEntity(user, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        //return new Exception("Account is inactive");
    }

    public String register(User user) {
        if (this.findByEmail(user.getEmail()) == null) {
            System.out.println("pass: "+user.getPassword());
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRoleList(new ArrayList<>());
            user.getRoleList().add(roleService.findByName("ROLE_MEMBER"));
            user.setActive(true);
            this.save(user);
            return "Register successful!";
        }
        return ("Email has already been used! Please register again!");
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User setAdmin(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        boolean alreadyAdmin = false;
        if (user.isPresent()) {
            for (Role r : user.get().getRoleList()) {
                if (r.getName().equals("ROLE_ADMIN")) alreadyAdmin = true;
            }
            if (alreadyAdmin == false) {
                user.get().getRoleList().add(roleService.findByName("ROLE_ADMIN"));
                userRepository.save(user.get());
            }
            return user.get();
        }
        throw new Exception();
    }

    public User removeAdmin(int id) throws Exception {
        Optional<User> user = userRepository.findById(id);
        boolean alreadyAdmin = false;
        if (user.isPresent()) {
            for (Role r : user.get().getRoleList()) {
                if (r.getName().equals("ROLE_ADMIN")) alreadyAdmin = true;
            }
            if (alreadyAdmin == true) {
                user.get().getRoleList().remove(roleService.findByName("ROLE_ADMIN"));
                userRepository.save(user.get());
            }
            return user.get();
        }
        throw new NotFoundException("Not found exception");
    }

    public User inactive(int id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setActive(false);
            userRepository.save(user.get());
            return user.get();
        }
        throw new NotFoundException("Not found exception");
    }

    public User active(int id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setActive(true);
            userRepository.save(user.get());
            return user.get();
        }
        throw new NotFoundException("Not found exception");
    }
}
