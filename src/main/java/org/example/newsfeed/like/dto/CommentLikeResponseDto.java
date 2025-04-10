package org.example.newsfeed.like.dto;

import lombok.Getter;

@Getter
public class CommentLikeResponseDto {

    private final Long id;

    private final int sumLike;

    private final boolean likeYN;

    public CommentLikeResponseDto(Long id,int sumLike, boolean likeYN) {
        this.id = id;
        this.sumLike = sumLike;
        this.likeYN = likeYN;
    }

}
