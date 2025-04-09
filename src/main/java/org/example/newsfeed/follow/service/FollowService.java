package org.example.newsfeed.follow.service;

import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;

import java.util.List;

public interface FollowService {

    FollowSingleResponseDto saveFollow(Long followerId, Long followingId);
    FollowSingleResponseDto updateFollow(Long followerId, Long followingId);
    List<FollowResponseDto> findFollowingsByMyId(Long myId);
    List<FollowResponseDto> findFollowersByMyId(Long myId);
    boolean existFollowTrue (Long followerId, Long followingId);
}
