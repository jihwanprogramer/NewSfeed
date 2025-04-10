package org.example.newsfeed.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newsfeed.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {

    private final Long id;
    private final Long boardId;
    private final Long userId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    
    public CommentResponseDto(Comment comment)
    {
        this.id = comment.getId();
        this.boardId = comment.getBoard().getId();
        this.userId = comment.getUser().getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
    }


}
