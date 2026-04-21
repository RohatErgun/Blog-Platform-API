package com.rohater.blog.services;

import com.rohater.blog.domain.CreatePostRequest;
import com.rohater.blog.domain.UpdatePostRequest;
import com.rohater.blog.domain.model.Post;
import com.rohater.blog.domain.model.User;

import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAllPosts(UUID categoryId, UUID tagId);
    List<Post> getDraftPosts(User user);
    Post createPost(User user, CreatePostRequest createPostRequest);
    Post updatePost(UUID id, UpdatePostRequest updatePostRequest);
    Post getPost(UUID id);
}
