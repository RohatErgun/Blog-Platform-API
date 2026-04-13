package com.rohater.blog.services.impl;

import com.rohater.blog.domain.model.Category;
import com.rohater.blog.repository.CategoryRepository;
import com.rohater.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAllByPostCount();
    }

    @Override
    @Transactional
    public Category createCategory(Category category) {
        if (categoryRepository.existByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException
                    ("Category with " + category.getName() + " is already exists");
        }
        return categoryRepository.save(category);
    }
}
