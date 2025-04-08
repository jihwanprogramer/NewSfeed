package org.example.newsfeed.follow.service;

import org.example.newsfeed.follow.dto.FollowResponseDto;

import java.util.List;

public interface FollowService {

    void saveFollow(Long followerId, Long followingId);
    boolean updateFollow(Long followerId, Long followingId);
    List<FollowResponseDto> findFollowingsByMyId(Long myId);
    List<FollowResponseDto> findFollowersByMyId(Long myId);
    boolean existFollowTrue (Long followerId, Long followingId);
}
