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
     * 팔로우를 생성합니다.
     *
     * @param followId 팔로우 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @return 생성된 팔로우 정보 DTO
     */
    @PostMapping("/{followId}/follow")
    public ResponseEntity<FollowSingleResponseDto> createFollow(
            @PathVariable Long followId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ){

        FollowSingleResponseDto followSingleResponseDto = followService.createFollow(loginUser.getId(),followId);

        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.CREATED);
    }

    /**
     * 팔로우 상태를 수정(토글)합니다.
     *
     * @param followingId 팔로우 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @return 수정된 팔로우 정보 DTO
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
     * 해당 사용자를 로그인한 사용자가 팔로우하고 있는지 확인합니다.
     *
     * @param userId 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @return 팔로우 상태 정보 DTO
     */
    @GetMapping("/{userId}/follow-status")
    public ResponseEntity<FollowSingleResponseDto> findFollowYN(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ){

        FollowSingleResponseDto followSingleResponseDto = followService.findFollowStatus(loginUser.getId(), userId);

        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.OK);
    }


    /**
     * 특정 사용자가 팔로우한 사용자 목록을 조회합니다. (following 목록)
     *
     * @param userId 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @param pageable 페이징 정보
     * @return 팔로우한 사용자 목록 DTO 페이지
     */
    @GetMapping("/{userId}/followings")
    public ResponseEntity<Page<FollowResponseDto>> findFollowingsByUserId(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser,
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
    ){

        followService.existFollowTrue(userId, loginUser.getId());

        return new ResponseEntity<>(followService.findFollowingsById(userId, pageable), HttpStatus.OK);
    }

    /**
     * 특정 사용자를 팔로우한 사용자 목록을 조회합니다. (follower 목록)
     *
     * @param userId 대상 사용자 ID
     * @param loginUser 로그인한 사용자 정보
     * @param pageable 페이징 정보
     * @return 팔로워 사용자 목록 DTO 페이지
     */
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Page<FollowResponseDto>> findFollowerByUserId(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser,
            @PageableDefault(size = 10, sort = "modifiedAt", direction = Sort.Direction.DESC) Pageable pageable
    ){

        followService.existFollowTrue(userId, loginUser.getId());

        return new ResponseEntity<>(followService.findFollowersById(userId, pageable),HttpStatus.OK);
    }

}
