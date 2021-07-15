package com.phuongtd.blog.repositories;

import com.phuongtd.blog.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TagRepository extends JpaRepository<Tag, Integer> {
}
