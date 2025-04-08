package org.example.newsfeed.follow.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.service.FollowService;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users/{followId}/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<Map<String,String>> saveFollow(
            @PathVariable Long followId,
            HttpSession session
    ){
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        followService.saveFollow(followId, loginUser.getId());

        return new ResponseEntity<>(Map.of("message", "팔로우 하였습니다."), HttpStatus.CREATED);
    }

    @PutMapping
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

    @GetMapping
    public List<FollowResponseDto> findFollowUsersByFllowingId(HttpSession session){

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        return followService.findFollowByFollowingId(loginUser.getId());
    }




}
