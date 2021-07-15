package com.trandiepphuong.blog.controller;

import com.trandiepphuong.blog.entities.Category;
import com.trandiepphuong.blog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAll() {
        return categoryService.getAll();
    }

    @PostMapping()
    public Category addCategory(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @PutMapping()
    public Category editCategory(@RequestBody Category category) {
        return categoryService.update(category.getId(), category);
    }
}
