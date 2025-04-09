package org.example.newsfeed.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.newsfeed.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentPageResponseDto {
    private final Long id;
    private final String content;
    private final LocalDateTime createAt;
    private final LocalDateTime updateAt;
    private final String userName;

    public CommentPageResponseDto(Comment comment){
        this.id= comment.getId();
        this.content =comment.getContent();
        this.createAt = comment.getCreateAt();
        this.updateAt = comment.getUpdatedAt();
        this.userName = comment.getUser().getName();
    }
}
