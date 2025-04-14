package org.example.newsfeed.follow.dto;

import lombok.Getter;
import org.example.newsfeed.follow.entity.Follow;
/**
 * 팔로우 사용자 정보를 응답으로 전달하는 DTO입니다.
 */
@Getter
public class FollowResponseDto {

    private final Long followId;

    private final String followName;

    /**
     * 생성자입니다.
     *
     * @param followId 팔로우 대상 사용자 ID
     * @param followName 팔로우 대상 사용자 이름
     */
    public FollowResponseDto(Long followId, String followName) {
        this.followId = followId;
        this.followName = followName;
    }

    /**
     * Follow 엔티티를 FollowResponseDto로 변환합니다.
     *
     * @param follow Follow 엔티티
     * @return 변환된 FollowResponseDto
     */
    public static FollowResponseDto toDto(Follow follow){
        return new FollowResponseDto(
                follow.getFollowingUser().getId(),
                follow.getFollowingUser().getName()
        );
    }
}
