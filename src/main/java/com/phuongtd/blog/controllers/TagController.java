package com.phuongtd.blog.controllers;

import com.phuongtd.blog.entities.Tag;
import com.phuongtd.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
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