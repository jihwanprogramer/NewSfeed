package org.example.newsfeed.follow.dto;

import lombok.Getter;

@Getter
public class FollowSingleResponseDto {

    private final Long id;

    private final boolean followYN;

    private final int followerCount;

    private final int followingCount;

    private final boolean isMyProfile;

    public FollowSingleResponseDto(Long id, boolean followYN, int followerCount, int followingCount, boolean isMyProfile) {
        this.id = id;
        this.followYN = followYN;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.isMyProfile = isMyProfile;
    }
}
