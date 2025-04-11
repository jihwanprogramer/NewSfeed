package org.example.newsfeed.follow.service;


import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface FollowService {

    /**
     * 팔로우를 생성합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 생성된 팔로우 정보 DTO
     */
    FollowSingleResponseDto createFollow(Long followerId, Long followingId);

    /**
     * 팔로우 상태를 수정(토글)합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 수정된 팔로우 정보 DTO
     */
    FollowSingleResponseDto updateFollow(Long followerId, Long followingId);

    /**
     * 특정 사용자를 로그인한 사용자가 팔로우하고 있는지 조회합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 팔로우 상태 정보 DTO
     */
    FollowSingleResponseDto findFollowStatus(Long followerId, Long followingId);

    /**
     * 특정 사용자가 팔로우한 사용자 목록을 조회합니다. (following 목록)
     *
     * @param id 사용자 ID
     * @param pageable 페이징 정보
     * @return 팔로우한 사용자 목록 DTO 페이지
     */
    Page<FollowResponseDto> findFollowingsById(Long id,Long loginUserId, Pageable pageable);

    /**
     * 특정 사용자를 팔로우한 사용자 목록을 조회합니다. (follower 목록)
     *
     * @param id 사용자 ID
     * @param pageable 페이징 정보
     * @return 팔로워 사용자 목록 DTO 페이지
     */
    Page<FollowResponseDto> findFollowersById(Long id, Long loginUserId, Pageable pageable);

    /**
     * 팔로우가 존재하는지 확인합니다. (팔로우 상태가 true인지 검증)
     *
     * @param followerId  팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     */
    default void existFollowTrue(Long followerId, User followingUser) {

    }

    /**
     * 특정 사용자를 팔로우한 유저 수를 반환합니다.
     *
     * @param followingID 팔로우 대상 사용자 ID
     * @param loginId 로그인한 사용자 ID
     * @return 팔로워 수
     */
    int countFollowByFollowingId(Long followingID, Long loginId);

    /**
     * 특정 사용자가 팔로우한 유저 수를 반환합니다.
     *
     * @param followerID 팔로우 요청자 ID
     * @param loginId 로그인한 사용자 ID
     * @return 팔로우 수
     */
    int countFollowByFollowerId(Long followerID, Long loginId);
}