package org.example.newsfeed.follow.service;

import org.example.newsfeed.follow.dto.FollowResponseDto;

import java.util.List;

public interface FollowService {

    void saveFollow(Long followId, Long followingId);
    boolean updateFollow(Long followId, Long followingId);
    List<FollowResponseDto> findFollowingsByMyId(Long myId);
    List<FollowResponseDto> findFollowersByMyId(Long myId);
}
