package org.example.newsfeed.follow.dto;

import lombok.Getter;

@Getter
public class FollowSingleResponseDto {

    private final Long id;

    private final boolean followYN;

    private final boolean isMyProfile;

    public FollowSingleResponseDto(Long id, boolean followYN, boolean isMyProfile) {
        this.id = id;
        this.followYN = followYN;
        this.isMyProfile = isMyProfile;
    }
}
