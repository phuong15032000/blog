package com.phuongtd.blog.controllers;

import com.phuongtd.blog.entities.Category;
import com.phuongtd.blog.entities.Post;
import com.phuongtd.blog.entities.User;
import com.phuongtd.blog.services.CategoryService;
import com.phuongtd.blog.services.PostService;
import com.phuongtd.blog.services.UserService;
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

    @Autowired
    CategoryService categoryService;

    @Autowired
    UserService userService;

    public AdminController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/browse")
    public List<Post> getAllPost() {
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

    @GetMapping("/category")
    public List<Category> getAllCategory() {
        return categoryService.getAll();
    }

    @PostMapping("/category")
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/category/{id}")
    public Category deleteById(@PathVariable int id) throws NotFoundException {
        return categoryService.deleteById(id);
    }

    @PutMapping("/category/{id}")
    public Category editCategory(@PathVariable int id, @RequestBody Category category) throws NotFoundException {
        return categoryService.update(id, category);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }

    @PutMapping("/users/setAdmin/{id}")
    public User setAdmin(@PathVariable int id) throws Exception {
        return userService.setAdmin(id);
    }

    @PutMapping("/users/removeAdmin/{id}")
    public User removeAdmin(@PathVariable int id) throws Exception {
        return userService.removeAdmin(id);
    }

    @PutMapping("/users/inactive/{id}")
    public User inactiveUser(@PathVariable int id) throws NotFoundException {
        return userService.inactive(id);
    }
    @PutMapping("/users/active/{id}")
    public User activeUser(@PathVariable int id) throws NotFoundException {
        return userService.active(id);
    }
    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable int id) throws NotFoundException {
        return userService.findById(id);
    }
    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user, @PathVariable int id) throws Exception {
        return userService.update(id,user);
    }
}
