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
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;
    private final String userName;

    /**
     * Comment 객체로부터 CommentPageResponseDto를 생성하는 생성자입니다.
     *
     * @param comment 댓글 객체
     */
    public CommentPageResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
        this.modifiedAt = comment.getModifiedAt();
        this.userName = comment.getUser().getName();
    }
}
