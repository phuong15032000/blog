package com.trandiepphuong.blog.services;

import com.trandiepphuong.blog.entities.Role;
import com.trandiepphuong.blog.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Access;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;


    public Role findByName(String name) {
        return roleRepository.findByName(name);
    }
}
