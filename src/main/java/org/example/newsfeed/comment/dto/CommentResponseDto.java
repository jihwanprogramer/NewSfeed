package org.example.newsfeed.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final Long postId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
}
