package org.example.newsfeed.follow.service;


import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {

    FollowSingleResponseDto saveFollow(Long followerId, Long followingId);
    FollowSingleResponseDto updateFollow(Long followerId, Long followingId);
    FollowSingleResponseDto findFollowStatus(Long followerId, Long followingId);
    Page<FollowResponseDto> findFollowingsById(Long id, Pageable pageable);
    Page<FollowResponseDto> findFollowersById(Long id, Pageable pageable);
    boolean existFollowTrue(Long followerId, Long followingId);
    int countFollowByFollowingId(Long followingID, Long loginId);
    int countFollowByFollowerId(Long followerID, Long loginId);
}