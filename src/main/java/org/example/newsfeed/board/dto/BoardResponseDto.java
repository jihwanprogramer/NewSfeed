package org.example.newsfeed.board.dto;

import lombok.Getter;
import org.example.newsfeed.board.entity.Board;

import java.time.LocalDateTime;

@Getter
public class BoardResponseDto {

    private Long id;

    private String username;

    private String title;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUser().getName();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.createdAt = board.getCreatedAt();
        this.modifiedAt = board.getModifiedAt();
    }

    public static BoardResponseDto findAll(Board board) {
        return new BoardResponseDto(board);
    }
}
