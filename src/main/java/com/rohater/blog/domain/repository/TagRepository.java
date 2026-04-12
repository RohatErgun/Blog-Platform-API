package com.rohater.blog.domain.repository;

import com.rohater.blog.domain.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TagRepository
        extends JpaRepository <Tag, UUID>{
}
