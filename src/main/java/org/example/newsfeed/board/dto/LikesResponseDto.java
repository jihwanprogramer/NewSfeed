package org.example.newsfeed.board.dto;

import lombok.Getter;
import org.example.newsfeed.board.entity.Board;

import java.time.LocalDateTime;

@Getter
public class LikesResponseDto {

    private Long id;

    private String username;

    private String title;

    private String comments;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    private int boardLikes;

    public LikesResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUser().getName();
        this.title = board.getTitle();
        this.comments = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
        this.boardLikes = board.getLikesCount();
    }
}
