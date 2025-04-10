package org.example.newsfeed.like.dto;

import lombok.Getter;
import org.example.newsfeed.like.entity.BoardLike;

@Getter
public class BoardLikeResponseDto {

    private final Long id;

    private final int sumLike;

    private final boolean likeYN;

    public BoardLikeResponseDto(Long id,int sumLike, boolean likeYN) {
        this.id = id;
        this.sumLike = sumLike;
        this.likeYN = likeYN;
    }

}

