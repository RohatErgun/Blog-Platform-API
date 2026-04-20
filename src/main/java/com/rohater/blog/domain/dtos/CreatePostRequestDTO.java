package com.rohater.blog.domain.dtos;

import com.rohater.blog.domain.PostStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreatePostRequestDTO {
    @NotBlank(message = "title name required")
    @Size(min = 2, max = 50, message = "Title should be between {min} and {max} characters")
    private String title;


    @NotBlank(message = "title name required")
    @Size(min = 10, max = 100000, message = "Content should be between {min} and {max} characters")
    private String content;

    @NotNull(message = "Category ID required")
    private UUID category;

    @Builder.Default
    @Size(max = 10, message = "Maximum {max} tags allowed")
    private Set<UUID> tagIds = new HashSet<>();

    @NotNull(message = "Status is required")
    private PostStatus status;
}
