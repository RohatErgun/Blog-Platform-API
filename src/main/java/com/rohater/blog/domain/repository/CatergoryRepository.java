package com.rohater.blog.domain.repository;

import com.rohater.blog.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CatergoryRepository
        extends JpaRepository <Category, UUID>{
}
