package com.example.whycors.domain.post.dto;

import lombok.Data;

@Data
public class PostRequestDto {
    private String title;
    private String content;
    private Boolean lock;
}
