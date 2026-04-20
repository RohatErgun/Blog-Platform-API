package com.rohater.blog.services;

import com.rohater.blog.domain.model.Post;
import com.rohater.blog.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
}
