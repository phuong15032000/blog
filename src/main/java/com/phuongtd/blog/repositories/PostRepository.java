package com.phuongtd.blog.repositories;

import com.phuongtd.blog.entities.Category;
import com.phuongtd.blog.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAll();
    List<Post> findByCategory(Optional<Category> category);
    List<Post> findByTagList_Name(String nameTag);
}
