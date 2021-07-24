package com.phuongtd.blog.controllers;

import com.phuongtd.blog.entities.Post;
import com.phuongtd.blog.services.PostService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
    @Autowired
    PostService postService;

    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/browse")
    public List<Post> getAllPost(){
        return postService.findAll();
    }
    @PutMapping("/browse/{id}")
    public Post activePost(@PathVariable int id, @RequestBody Post post) throws NotFoundException, ParseException {
        return postService.activePost(id, post);
    }
    @PutMapping("/browse/inactive/{id}")
    public Post inactivePost(@PathVariable int id, @RequestBody Post post) throws NotFoundException, ParseException {
        return postService.inactivePost(id, post);
    }
}
