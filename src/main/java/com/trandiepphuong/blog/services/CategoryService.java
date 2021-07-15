package com.trandiepphuong.blog.services;

import com.trandiepphuong.blog.entities.Category;
import com.trandiepphuong.blog.repositories.CategoryRepository;
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
    public Category update(int id, Category newCategory) {
        Optional<Category> presentCategory = categoryRepository.findById(id);
        if (presentCategory.isPresent()){
            presentCategory.get().setName(newCategory.getName());
            return categoryRepository.save(presentCategory.get());
        }
        return null;
    }
}
