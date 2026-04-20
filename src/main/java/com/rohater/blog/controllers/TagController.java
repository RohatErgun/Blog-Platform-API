package com.rohater.blog.controllers;

import com.rohater.blog.domain.dtos.CreateTagRequest;
import com.rohater.blog.domain.dtos.TagResponse;
import com.rohater.blog.domain.model.Tag;
import com.rohater.blog.mappers.TagMapper;
import com.rohater.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagResponse>> getAllTags(){
        List<Tag> tags = tagService.getTags();
        List<TagResponse> tagResponses = tags.stream().map(tagMapper::toTagResponse).toList();

        return ResponseEntity.ok(tagResponses);
    }

    @PostMapping
    public ResponseEntity<List<TagResponse>> createTags(@RequestBody CreateTagRequest createTagRequest){
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());
        List<TagResponse> createTagResponses = savedTags.stream().map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(createTagResponses, HttpStatus.CREATED);
    }
}
