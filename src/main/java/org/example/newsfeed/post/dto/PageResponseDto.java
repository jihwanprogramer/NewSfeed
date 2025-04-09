package org.example.newsfeed.post.dto;

import lombok.Getter;
import org.example.newsfeed.post.entity.Board;

import java.time.LocalDateTime;

@Getter
public class PageResponseDto {

    private Long id;

    private String username;

    private String title;

    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PageResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUser().getName();
        this.title = board.getTitle();
        this.comments = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }
}
