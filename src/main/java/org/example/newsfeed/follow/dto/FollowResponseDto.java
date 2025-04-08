package org.example.newsfeed.follow.dto;

import lombok.Getter;
import org.example.newsfeed.follow.entity.Follow;

@Getter
public class FollowResponseDto {

    private final String FollowName;

    public FollowResponseDto(String followName) {
        FollowName = followName;
    }

    public static FollowResponseDto toDto(Follow follow){
        return new FollowResponseDto(follow.getFollowUsers().getName());
    }

}
