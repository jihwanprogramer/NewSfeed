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
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followId}/follow")
    public ResponseEntity<FollowSingleResponseDto> saveFollow(
            @PathVariable Long followId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ){

        FollowSingleResponseDto followSingleResponseDto = followService.saveFollow(loginUser.getId(),followId);

        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{followingId}/follow")
    public ResponseEntity<FollowSingleResponseDto> updateFollow(
            @PathVariable Long followingId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ) {

        FollowSingleResponseDto followSingleResponseDto = followService.updateFollow(loginUser.getId(), followingId);

        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.OK);

    }

    //내가 본 대상이 내가 팔로우 했는지 확인
    @GetMapping("/{userId}/follow-status")
    public ResponseEntity<FollowSingleResponseDto> findFollowYN(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser
    ){

        FollowSingleResponseDto followSingleResponseDto = followService.findFollowStatus(loginUser.getId(), userId);

        return new ResponseEntity<>(followSingleResponseDto, HttpStatus.OK);
    }


    // 특정 유저의 팔로우한 유저 목록 조회 following 목록
    @GetMapping("/{userId}/followings")
    public ResponseEntity<Page<FollowResponseDto>> findFollowingsByUserId(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser,
            @PageableDefault(size = 10, sort = "name") Pageable pageable
    ){

        followService.existFollowTrue(userId, loginUser.getId());

        return new ResponseEntity<>(followService.findFollowingsById(userId, pageable), HttpStatus.OK);
    }

    // 특정 유저를 팔로우한 유저 목록 조회 follower 목록
    @GetMapping("/{userId}/followers")
    public ResponseEntity<Page<FollowResponseDto>> findFollowerByUserId(
            @PathVariable Long userId,
            @SessionAttribute(name = Const.LOGIN_USER) UserResponseDto loginUser,
            @PageableDefault(size = 10, sort = "name") Pageable pageable
    ){

        followService.existFollowTrue(userId, loginUser.getId());

        return new ResponseEntity<>(followService.findFollowersById(userId, pageable),HttpStatus.OK);
    }

}
