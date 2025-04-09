package org.example.newsfeed.follow.service;

import org.example.newsfeed.follow.dto.FollowCountResponseDto;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;

import java.util.List;

public interface FollowService {

    FollowSingleResponseDto saveFollow(Long followerId, Long followingId);
    FollowSingleResponseDto updateFollow(Long followerId, Long followingId);
    FollowSingleResponseDto findFollowStatus(Long followerId, Long followingId);
    List<FollowResponseDto> findFollowingsById(Long id);
    List<FollowResponseDto> findFollowersById(Long id);
    boolean existFollowTrue(Long followerId, Long followingId);
    FollowCountResponseDto countFollowByFollowingId(Long followingID);
    FollowCountResponseDto countFollowByFollowerId(Long followerID);
}