package com.rohater.blog.mappers;

import com.rohater.blog.domain.PostStatus;
import com.rohater.blog.domain.dtos.CategoryDTO;
import com.rohater.blog.domain.dtos.CreateCategoryRequest;
import com.rohater.blog.domain.model.Category;
import com.rohater.blog.domain.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDTO toDTo(Category category);

    Category toEntity(CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount(List<Post> posts){
        if (null == posts){
            return 0;
        }
        return posts.stream().
                filter(
                        post -> PostStatus.PUBLISHED.equals(post.getStatus())
                ).count();
    }
}
