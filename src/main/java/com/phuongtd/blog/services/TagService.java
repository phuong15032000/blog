package com.phuongtd.blog.services;

import com.phuongtd.blog.entities.Tag;
import com.phuongtd.blog.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;
    public List<Tag> findAll() {
        return tagRepository.findAll();
    }
}