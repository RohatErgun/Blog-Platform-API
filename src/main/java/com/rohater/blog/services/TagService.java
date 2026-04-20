package com.rohater.blog.services;

import com.rohater.blog.domain.model.Tag;

import java.util.List;
import java.util.Set;

public interface TagService {
    List<Tag> getTags();
    List<Tag> createTags(Set<String> names);
}
