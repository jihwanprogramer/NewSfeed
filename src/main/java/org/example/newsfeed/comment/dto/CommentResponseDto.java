package org.example.newsfeed.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newsfeed.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final Long postId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static CommentResponseDto toDto(Comment comment){
        return new CommentResponseDto(comment.getId(),comment.getPost().getId(), comment.getContent(), comment.getCreateAt(),comment.getUpdatedAt());
    }
}
