package com.rohater.blog.repository;

import com.rohater.blog.domain.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository
        extends JpaRepository <Post, UUID>{
}
