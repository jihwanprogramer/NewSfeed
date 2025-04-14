package org.example.newsfeed.like.dto;

import lombok.Getter;
import org.example.newsfeed.like.entity.BoardLike;

@Getter
public class BoardLikeResponseDto {
    /**
     * 좋아요 정보를 응답으로 전달 하는 DTO
     */
    private final Long id;

    private final int sumLike;

    private final boolean likeYN;

    /**
     * 생성자
     *
     * @param id 게시글 좋아요 ID
     * @param sumLike 좋아요의 합
     * @param likeYN 좋아요 여부
     *
     */
    public BoardLikeResponseDto(Long id,int sumLike, boolean likeYN) {
        this.id = id;
        this.sumLike = sumLike;
        this.likeYN = likeYN;
    }

}

