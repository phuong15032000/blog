package com.trandiepphuong.blog.controllers;

import com.trandiepphuong.blog.entities.Post;
import com.trandiepphuong.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.web.servlet.headers.HeadersSecurityMarker;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
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

    @GetMapping("/{categoryId}")
    public List<Post> getPostsByCategory(@PathVariable int categoryId) {
        return postService.findByCategory(categoryId);
    }

    @GetMapping("/post")
    public Optional<Post> getPostById(@RequestParam int id){
        return postService.findById(id);
    }

    @PostMapping("/create")
    public Post createPost(@RequestBody Post post){
        return postService.save(post);
    }
}
