package com.rohater.blog.controllers;

import com.rohater.blog.domain.model.Category;
import com.rohater.blog.mappers.CategoryMapper;
import com.rohater.blog.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rohater.blog.domain.dtos.CategoryDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories ")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> listCategories(){
        List<CategoryDTO> categories = categoryService.listCategories()
                .stream().map(category -> categoryMapper.toDTo(category))
                .toList();

        return ResponseEntity.ok(categories);
    }
}
