package com.phuongtd.blog.controllers;

import com.phuongtd.blog.entities.Post;
import com.phuongtd.blog.services.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    @Autowired
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping()
    public List<Post> getActivedPost() {
        return postService.getActivedPost();
    }

    @GetMapping("/{categoryId}")
    public List<Post> getPostByCategory(@PathVariable int categoryId) {
        return postService.findByCategory(categoryId);
    }

    @GetMapping("/post")
    public Optional<Post> getPostById(@RequestParam int id) {
        return postService.findById(id);
    }

    @PostMapping()
    public Post createPost(@RequestBody Post post) throws ParseException {
        return postService.save(post);
    }

    @GetMapping("/tag")
    public List<Post> getPostByTag(@RequestParam String nameTag) {
        return postService.findByTag(nameTag);
    }

    @DeleteMapping("/{id}")
    public Post deletePost(@PathVariable int id) throws NotFoundException {
        return postService.deletePost(id);
    }

    @PutMapping("/edit/{id}")
    public Post editPost(@PathVariable int id, @RequestBody Post newPost) throws ParseException, NotFoundException {
        return postService.update(id, newPost);
    }

    @GetMapping("/userId")
    public List<Post> listPostByUserId(@RequestParam int id){
        return postService.findByUser(id);
    }
}