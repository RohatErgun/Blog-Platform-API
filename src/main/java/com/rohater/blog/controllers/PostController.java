package com.rohater.blog.controllers;

import com.rohater.blog.domain.dtos.PostDTO;
import com.rohater.blog.domain.model.Post;
import com.rohater.blog.mappers.PostMapper;
import com.rohater.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final PostMapper postMapper;
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts(
            @RequestParam(required = false) UUID categoryId,
            @RequestParam(required = false) UUID tagId) {
        List<Post> posts = postService.getAllPosts(categoryId, tagId);
        List<PostDTO> postDTOs =posts.stream().map(postMapper::toDTO).toList();

        return ResponseEntity.ok(postDTOs);
    }


//    @PostMapping
//    public ResponseEntity<PostDTO> createPost(){
//        // todo
//    }
//
//    @DeleteMapping(path = "/{id}")
//    public ResponseEntity<Void> deletePost(@PathVariable UUID id){
//        // todo
//    }
}
