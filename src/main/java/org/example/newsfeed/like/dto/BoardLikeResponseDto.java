package org.example.newsfeed.like.dto;

import lombok.Getter;

@Getter
public class BoardLikeResponseDto {

    private final Long id;

    private final boolean likeYN;

    public BoardLikeResponseDto(Long id, boolean likeYN) {
        this.id = id;
        this.likeYN = likeYN;
    }
}
