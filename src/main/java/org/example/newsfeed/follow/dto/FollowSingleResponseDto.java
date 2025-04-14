package org.example.newsfeed.follow.dto;

import lombok.Getter;
/**
 * 단일 팔로우 상태 및 관련 정보를 담는 응답 DTO입니다.
 */
@Getter
public class FollowSingleResponseDto {

    private final Long id;

    private final boolean followYN;

    private final int followerCount;

    private final int followingCount;

    private final boolean isMyProfile;

    /**
     * 모든 필드를 초기화하는 생성자입니다.
     *
     * @param id 팔로우 ID
     * @param followYN 팔로우 상태
     * @param followerCount 팔로워 수
     * @param followingCount 팔로잉 수
     * @param isMyProfile 내 프로필 여부
     */
    public FollowSingleResponseDto(Long id, boolean followYN, int followerCount, int followingCount, boolean isMyProfile) {
        this.id = id;
        this.followYN = followYN;
        this.followerCount = followerCount;
        this.followingCount = followingCount;
        this.isMyProfile = isMyProfile;
    }
}