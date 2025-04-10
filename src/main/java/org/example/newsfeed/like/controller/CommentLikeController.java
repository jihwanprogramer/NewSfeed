package org.example.newsfeed.like.controller;

import jakarta.servlet.http.HttpSession;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.like.Service.CommentLikeService;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeService commentLikeService;

    @PostMapping("/{commentid}/like")
    public ResponseEntity<CommentLikeResponseDto> likeYN(@PathVariable Long commentid, HttpSession session){
        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER) ;

        CommentLikeResponseDto commentLikeResponseDto = commentLikeService.saveLikeYN(commentid, loginUser.getId());

        return new ResponseEntity<>(commentLikeResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{commentid}/like")
    public ResponseEntity<Map<String, String>> changeLikeYN(@PathVariable Long commentid, HttpSession session){
        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER);

        boolean likeYN = commentLikeService.changeLikeYN(commentid, loginUser.getId());

        if(likeYN){
            return new ResponseEntity<>(Map.of("message", "다시 좋아요 하였습니다."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "좋아요를 취소하였습니다."), HttpStatus.OK);
        }
    }

//    @GetMapping("/{commentid}/like/{likeid}")
//    public
}
