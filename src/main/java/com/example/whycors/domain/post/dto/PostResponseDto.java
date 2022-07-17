package com.example.whycors.domain.post.dto;

import com.example.whycors.domain.post.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private LocalDateTime created_At;
    private String memberNickname;
    private Long memberId;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.created_At = post.getCreatedAt();
        this.memberNickname = post.getMember().getNickname();
        this.memberId = post.getMember().getId();
    }
}
