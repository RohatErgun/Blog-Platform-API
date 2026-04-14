package com.rohater.blog.services.impl;

import com.rohater.blog.domain.model.Category;
import com.rohater.blog.repository.CategoryRepository;
import com.rohater.blog.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        if (categoryRepository.existsByNameIgnoreCase(category.getName())){
            throw new IllegalArgumentException
                    ("Category with " + category.getName() + " is already exists");
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(UUID id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()){

            if(!category.get().getPosts().isEmpty()){
                throw new IllegalStateException("This category has posts associate with it");
            }
            categoryRepository.deleteById(id);
        }
    }
}
