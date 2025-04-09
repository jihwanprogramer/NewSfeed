package org.example.newsfeed.follow.dto;

import lombok.Getter;
import org.example.newsfeed.follow.entity.Follow;

@Getter
public class FollowResponseDto {

    private final Long followId;
    private final String followName;

    public FollowResponseDto(Long followId, String followName) {
        this.followId = followId;
        this.followName = followName;
    }

    public static FollowResponseDto toDto(Follow follow){
        return new FollowResponseDto(follow.getFollowingUsers().getId(), follow.getFollowingUsers().getName());
    }



}
