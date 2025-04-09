package org.example.newsfeed.like.dto;

import lombok.Getter;

@Getter
public class CommentLikeResponseDto {

    private final Long id;

    private final boolean likeYN;

    public CommentLikeResponseDto(Long id, boolean likeYN) {
        this.id = id;
        this.likeYN = likeYN;
    }
}
