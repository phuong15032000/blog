package com.phuongtd.blog.services;

import com.phuongtd.blog.entities.Category;
import com.phuongtd.blog.repositories.CategoryRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category update(int id, Category newCategory) throws NotFoundException {
        Optional<Category> presentCategory = categoryRepository.findById(id);
        if (presentCategory.isPresent()) {
            presentCategory.get().setName(newCategory.getName());
            return categoryRepository.save(presentCategory.get());
        }
        throw new NotFoundException("Not found exception");
    }

    public Category deleteById(int id) throws NotFoundException {
        try {
            Category category = categoryRepository.findById(id).get();
            categoryRepository.deleteById(id);
            return category;
        } catch (Exception e) {
            throw new NotFoundException(e.toString());
        }
    }
}