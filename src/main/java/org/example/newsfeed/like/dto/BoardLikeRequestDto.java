package org.example.newsfeed.like.dto;

import lombok.Getter;

@Getter
public class BoardLikeRequestDto {

    private final Long boardId;

    private final Long userId;

    private final boolean likeYN;

    public BoardLikeRequestDto(Long boardId, Long userId, boolean likeYN) {
        this.boardId = boardId;
        this.userId = userId;
        this.likeYN = likeYN;
    }

}
