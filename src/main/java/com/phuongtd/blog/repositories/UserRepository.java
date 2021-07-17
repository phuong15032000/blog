package com.phuongtd.blog.repositories;

import com.phuongtd.blog.entities.Tag;
import com.phuongtd.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);
}
