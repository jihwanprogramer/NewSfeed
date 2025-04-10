package org.example.newsfeed.follow.dto;

import lombok.Getter;

@Getter
public class FollowCountResponseDto {

    private final Long followCount;
    private final boolean isMyProfile;

    public FollowCountResponseDto(Long followCount, boolean isMyProfile) {
        this.followCount = followCount;
        this.isMyProfile = isMyProfile;
    }
}
