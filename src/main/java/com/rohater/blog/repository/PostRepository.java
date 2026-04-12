package com.rohater.blog.repository;

import com.rohater.blog.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PostRepository
        extends JpaRepository <Post, UUID>{
}
