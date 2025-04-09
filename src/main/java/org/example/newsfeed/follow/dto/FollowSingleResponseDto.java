package org.example.newsfeed.follow.dto;

import lombok.Getter;

@Getter
public class FollowSingleResponseDto {

    private final Long id;

    private final boolean followYN;

    public FollowSingleResponseDto(Long id, boolean followYN) {
        this.id = id;
        this.followYN = followYN;
    }
}
