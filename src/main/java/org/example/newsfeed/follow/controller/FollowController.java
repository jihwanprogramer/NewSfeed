package org.example.newsfeed.follow.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.exception.AccessDeniedException;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.service.FollowService;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/{followId}/follow")
    public ResponseEntity<Map<String,String>> saveFollow(
            @PathVariable Long followId,
            HttpSession session
    ){
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        followService.saveFollow(followId, loginUser.getId());

        return new ResponseEntity<>(Map.of("message", "팔로우 하였습니다."), HttpStatus.CREATED);
    }

    @PutMapping("/{followId}/follow")
    public ResponseEntity<Map<String,String>> updateFollow(
            @PathVariable Long followId,
            HttpSession session ) {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        boolean followYN = followService.updateFollow(followId, loginUser.getId());

        if(followYN){
            return new ResponseEntity<>(Map.of("message", "다시 팔로우 하였습니다."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "팔로우를 취소하였습니다."), HttpStatus.OK);
        }
    }

    // 로그인한 사용자가 팔로우한 유저 목록 조회 following 목록
    @GetMapping("/followings")
    public List<FollowResponseDto> findMyFollowings(HttpSession session){

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        return followService.findFollowingsByMyId(loginUser.getId());
    }

    // 로그인한 사용자를 팔로우한 유저 목록 조회 follower 목록
    @GetMapping("/followers")
    public List<FollowResponseDto> findMyFollower(HttpSession session){

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        return followService.findFollowersByMyId(loginUser.getId());
    }

    // 특정 유저의 팔로우한 유저 목록 조회 following 목록
    @GetMapping("/{userId}/followings")
    public List<FollowResponseDto> findFollowingsByUserId(@PathVariable Long userId, HttpSession session){

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        if(!followService.existFollowTrue(userId, loginUser.getId())){
            throw new AccessDeniedException("이 유저가 당신을 팔로워 해야 볼 수 있습니다.");
        }

        return followService.findFollowingsByMyId(userId);
    }

    // 특정 유저를 팔로우한 유저 목록 조회 follower 목록
    @GetMapping("/{userId}/followers")
    public List<FollowResponseDto> findFollowerByUserId(@PathVariable Long userId, HttpSession session){

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        if(!followService.existFollowTrue(userId, loginUser.getId())){
            throw new AccessDeniedException("이 유저가 당신을 팔로워 해야 볼 수 있습니다.");
        }

        return followService.findFollowersByMyId(userId);
    }


}
