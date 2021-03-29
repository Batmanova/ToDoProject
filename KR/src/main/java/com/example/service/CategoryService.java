package com.example.service;

import com.example.entity.Category;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(Category category){
        categoryRepository.save(category);
    }

    public void update(Category category){
        categoryRepository.save(category);
    }

    public void delete(Category category){
        categoryRepository.delete(category);
    }

    public List<Category> findAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> find(Long id){
        return categoryRepository.findById(id);
    }

}
