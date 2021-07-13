package com.trandiepphuong.blog.services;

import com.trandiepphuong.blog.entities.Post;
import com.trandiepphuong.blog.repositories.CategoryRepository;
import com.trandiepphuong.blog.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public List<Post> findByCategory(int categoryId){
        return postRepository.findByCategory(categoryRepository.findById(categoryId));
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}
