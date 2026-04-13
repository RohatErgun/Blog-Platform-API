package com.rohater.blog.services.impl;

import com.rohater.blog.domain.model.Category;
import com.rohater.blog.repository.CatergoryRepository;
import com.rohater.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CatergoryRepository catergoryRepository;

    @Override
    public List<Category> listCategories() {
        return List.of();
    }
}
