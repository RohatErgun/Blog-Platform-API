package com.rohater.blog.services;

import java.util.List;
import java.util.UUID;

import com.rohater.blog.domain.model.Category;

public interface CategoryService {
    List<Category> listCategories();
    Category createCategory(Category category);
    void deleteCategory(UUID id);
    Category getCategoryById(UUID id);
}
