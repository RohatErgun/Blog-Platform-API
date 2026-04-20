package com.rohater.blog.controllers;

import com.rohater.blog.domain.dtos.CreateTagRequest;
import com.rohater.blog.domain.dtos.TagDTO;
import com.rohater.blog.domain.model.Tag;
import com.rohater.blog.mappers.TagMapper;
import com.rohater.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;
    private final TagMapper tagMapper;

    @GetMapping
    public ResponseEntity<List<TagDTO>> getAllTags(){
        List<Tag> tags = tagService.getTags();
        List<TagDTO> tagResponse = tags.stream().map(tagMapper::toTagResponse).toList();

        return ResponseEntity.ok(tagResponse);
    }

    @PostMapping
    public ResponseEntity<List<TagDTO>> createTags(@RequestBody CreateTagRequest createTagRequest){
        List<Tag> savedTags = tagService.createTags(createTagRequest.getNames());
        List<TagDTO> createTagResponse = savedTags.stream().map(tagMapper::toTagResponse).toList();

        return new ResponseEntity<>(createTagResponse, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTags(@PathVariable UUID id){
        tagService.deleteTag(id);
        return ResponseEntity.noContent().build();
    }
}
