package org.example.newsfeed.post.dto;

import lombok.Getter;
import org.example.newsfeed.post.entity.Board;

import java.time.LocalDateTime;

@Getter
public class CreateResponseDto {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createdAt;

    public CreateResponseDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
    }
}
