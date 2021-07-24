package com.phuongtd.blog.repositories;

import com.phuongtd.blog.entities.Category;
import com.phuongtd.blog.entities.Post;
import com.phuongtd.blog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> findAllByOrderByCreatedAtDesc();
    List<Post> findByCategory(Optional<Category> category);
    List<Post> findByTagList_Name(String nameTag);
    List<Post> findByUser(Optional<User> byId);
}