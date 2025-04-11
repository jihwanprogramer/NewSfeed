package org.example.newsfeed.follow.controller;


import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.exception.AccessDeniedException;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;
import org.example.newsfeed.follow.service.FollowService;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * 팔로우 관련 요청을 처리하는 컨트롤러입니다.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    /**
     * 사용자가 다른 사용자를 팔로우합니다.
     *
     * @param followId 팔로우 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @return 생성된 팔로우 정보 DTO
     */
    @PostMapping("/{followId}/follow")
    public ResponseEntity<FollowSingleResponseDto> createFollow(
            @PathVariable Long followId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ) {
        FollowSingleResponseDto followSingleResponseDto = followService.createFollow(loginUser.getId(), followId);
        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 팔로우 상태를 토글합니다. (팔로우 ↔ 언팔로우)
     *
     * @param followingId 팔로우 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @return 변경된 팔로우 정보 DTO
     */
    @PatchMapping("/{followingId}/follow")
    public ResponseEntity<FollowSingleResponseDto> updateFollow(
            @PathVariable Long followingId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ) {
        FollowSingleResponseDto followSingleResponseDto = followService.updateFollow(loginUser.getId(), followingId);
        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.OK);
    }

    /**
     * 로그인한 사용자가 특정 사용자를 팔로우 중인지 확인합니다.
     *
     * @param userId 확인할 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @return 팔로우 여부 및 관련 정보 DTO
     */
    @GetMapping("/{userId}/follow-status")
    public ResponseEntity<FollowSingleResponseDto> findFollowYN(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ) {
        FollowSingleResponseDto followSingleResponseDto = followService.findFollowStatus(loginUser.getId(), userId);
        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.OK);
    }

    /**
     * 특정 사용자가 팔로우한 사용자 목록(followings)을 조회합니다.
     *
     * @param userId 조회 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @param pageable 페이징 및 정렬 정보
     * @return 팔로우한 사용자 목록 DTO 페이지
     */
    @GetMapping("/{userId}/followings")
    public ResponseEntity<Page<FollowResponseDto>> findFollowingsByUserId(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser,
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(followService.findFollowingsById(userId, loginUser.getId(), pageable), HttpStatus.OK);
    }

    /**
     * 특정 사용자를 팔로우한 사용자 목록(followers)을 조회합니다.
     *
     * @param userId 조회 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @param pageable 페이징 및 정렬 정보
     * @return 팔로워 사용자 목록 DTO 페이지
     */
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Page<FollowResponseDto>> findFollowerByUserId(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser,
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        return new ResponseEntity<>(followService.findFollowersById(userId, loginUser.getId(), pageable), HttpStatus.OK);
    }
}
