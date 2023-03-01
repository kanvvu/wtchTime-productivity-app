package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Category;
import com.example.demo.repository.categoryRepository;

@Service
public class categoryService {

    private final categoryRepository categoryRepository;

    @Autowired
    public categoryService(categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllItems() {
        return categoryRepository.findAll();
    }

    public void addNewItem(Category category) {
        categoryRepository.save(category);
    }

    public void deleteItemById(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new IllegalStateException("category doesnt exist");
        }
        categoryRepository.deleteById(id);
    }

    public Category getItemById(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category = null;
        if(optional.isPresent()) {
            category = optional.get();
        } else {
            throw new RuntimeException(" Category not found for id :: " + id);
        }

        return category;
    }
    
}
