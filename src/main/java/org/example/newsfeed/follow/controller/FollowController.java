package org.example.newsfeed.follow.controller;


import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.follow.service.FollowService;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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






}
