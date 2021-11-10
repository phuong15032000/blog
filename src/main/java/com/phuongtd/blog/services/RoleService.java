package com.phuongtd.blog.services;

import com.phuongtd.blog.entities.Role;
import com.phuongtd.blog.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
