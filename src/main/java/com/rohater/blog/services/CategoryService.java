package com.rohater.blog.services;

import java.util.List;

import com.rohater.blog.domain.model.Category;

public interface CategoryService {

    List<Category> listCategories();
    Category createCategory(Category category);
}
