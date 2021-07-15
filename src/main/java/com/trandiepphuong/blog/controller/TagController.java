package com.trandiepphuong.blog.controller;

import com.trandiepphuong.blog.entities.Tag;
import com.trandiepphuong.blog.repositories.TagRepository;
import com.trandiepphuong.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TagController {
    @Autowired
    TagService tagService;

    @GetMapping()
    public List<Tag> getAll(){
        return tagService.findAll();
    }
}
