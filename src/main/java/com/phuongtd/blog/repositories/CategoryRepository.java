package com.phuongtd.blog.repositories;

import com.phuongtd.blog.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findAll();

    Category findByNameContaining(String name);
}
