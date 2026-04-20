package com.rohater.blog.mappers;

import com.rohater.blog.domain.CreatePostRequest;
import com.rohater.blog.domain.dtos.CreatePostRequestDTO;
import com.rohater.blog.domain.dtos.PostDTO;
import com.rohater.blog.domain.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    @Mapping(target = "author", source = "author")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "tags", source = "tags")
    PostDTO toDTO(Post post);

    CreatePostRequest toCreatePostRequest(CreatePostRequestDTO dto);
}
