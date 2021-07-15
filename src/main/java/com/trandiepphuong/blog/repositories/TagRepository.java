package com.trandiepphuong.blog.repositories;

import com.trandiepphuong.blog.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Integer> {
}
