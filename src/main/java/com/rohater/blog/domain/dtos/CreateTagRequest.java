package com.rohater.blog.domain.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTagRequest {

    @NotEmpty(message = "At least one tag required")
    @Size(max = 10, message = "Maximum {max} tags allowed at once")
    private Set<
            @Size(min = 2, max = 50, message = "Tag name must be between {min} and {max} characters")
                    String> names;
}
