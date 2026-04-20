package com.rohater.blog.services.impl;

import com.rohater.blog.domain.model.Tag;
import com.rohater.blog.repository.TagRepository;
import com.rohater.blog.services.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public List<Tag> getTags() {
        return tagRepository.findAllWithPostCount();
    }
}
