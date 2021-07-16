package com.trandiepphuong.blog.configurations;

import com.trandiepphuong.blog.entities.Role;
import com.trandiepphuong.blog.entities.User;
import com.trandiepphuong.blog.repositories.RoleRepository;
import com.trandiepphuong.blog.repositories.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Configuration
public class DataSeedingListener implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${jwt-key}")
    private String signingKey;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private void addUserIfMissing(String email, String password, String... roles){
        if (userRepository.findByEmail(email) == null) {
            User user = new User();
            user.setEmail(email);
            user.setPassword(new BCryptPasswordEncoder().encode(password));
            user.setRoleList(new ArrayList<>());

            for (String role: roles) {
                user.getRoleList().add(roleRepository.findByName(role));
            }
            userRepository.save(user);
        }
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addUserIfMissing("user", "456", "ROLE_MEMBER");
        addUserIfMissing("admin", "123", "ROLE_MEMBER", "ROLE_ADMIN");
        if(signingKey == null || signingKey.length() ==0){
            String jws = Jwts.builder()
                    .setSubject("Blog")
                    .signWith(SignatureAlgorithm.HS256, "BlogApi").compact();
        }
    }
}
