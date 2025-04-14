package org.example.newsfeed.follow.service;


import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
/**
 * 팔로우 기능과 관련된 비즈니스 로직을 정의하는 서비스 인터페이스입니다.
 */
public interface FollowService {

    /**
     * 사용자가 다른 사용자를 팔로우합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 생성된 팔로우 정보 DTO
     */
    FollowSingleResponseDto createFollow(Long followerId, Long followingId);

    /**
     * 팔로우 상태를 토글합니다. (팔로우 ↔ 언팔로우)
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 변경된 팔로우 정보 DTO
     */
    FollowSingleResponseDto updateFollow(Long followerId, Long followingId);

    /**
     * 로그인한 사용자가 특정 사용자를 팔로우하고 있는지 여부를 조회합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 팔로우 상태 정보 DTO
     */
    FollowSingleResponseDto findFollowStatus(Long followerId, Long followingId);

    /**
     * 사용자가 팔로우한 사용자 목록(followings)을 조회합니다.
     *
     * @param id 대상 사용자 ID
     * @param loginUserId 로그인한 사용자 ID
     * @param pageable 페이징 정보
     * @return 팔로우한 사용자 목록 DTO 페이지
     */
    Page<FollowResponseDto> findFollowingsById(Long id, Long loginUserId, Pageable pageable);

    /**
     * 사용자를 팔로우한 사용자 목록(followers)을 조회합니다.
     *
     * @param id 대상 사용자 ID
     * @param loginUserId 로그인한 사용자 ID
     * @param pageable 페이징 정보
     * @return 팔로워 목록 DTO 페이지
     */
    Page<FollowResponseDto> findFollowersById(Long id, Long loginUserId, Pageable pageable);

    /**
     * 팔로우가 존재하고, 그 상태가 true인지 확인합니다.
     * 권한이 없는 경우 예외를 발생시킬 수 있습니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingUser 팔로우 대상 사용자 엔티티
     */
    default void existFollowTrue(Long followerId, User followingUser) {
        // 구현 클래스에서 override 필요
    }

    /**
     * 특정 사용자를 팔로우한 사용자 수를 반환합니다.
     *
     * @param followingID 팔로우 대상 사용자 ID
     * @param loginId 로그인한 사용자 ID
     * @return 팔로워 수
     */
    int countFollowByFollowingId(Long followingID, Long loginId);

    /**
     * 특정 사용자가 팔로우한 사용자 수를 반환합니다.
     *
     * @param followerID 팔로우 요청자 ID
     * @param loginId 로그인한 사용자 ID
     * @return 팔로우 수
     */
    int countFollowByFollowerId(Long followerID, Long loginId);
}