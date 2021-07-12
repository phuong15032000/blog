package com.trandiepphuong.blog.controller;

import com.trandiepphuong.blog.entities.Category;
import com.trandiepphuong.blog.entities.Post;
import com.trandiepphuong.blog.entities.Tag;
import com.trandiepphuong.blog.services.PostService;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blog")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<Post> getAll() {
        return postService.findAll();
    }

    @GetMapping("/{categoryName}") //%20
    public List<Post> getSportPosts(@PathVariable String categoryName) {
        return postService.findByCategory(categoryName);
    }

    @GetMapping("/post")
    public Optional<Post> getPostById(@RequestParam int id){
        return postService.findById(id);
    }
}
