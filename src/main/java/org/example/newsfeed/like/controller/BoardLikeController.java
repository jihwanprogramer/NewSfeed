
package org.example.newsfeed.like.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.like.Service.BoardLikeService;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

    @PostMapping("/{boardid}/like")
    public ResponseEntity<BoardLikeResponseDto> likeYN(@PathVariable Long boardid, HttpSession session) {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER); // 오브젝트를 그대로 쓸수 없어서 다운케스팅

        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.saveLikeYN(boardid, loginUser.getId());

        return new ResponseEntity<>(boardLikeResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{boardid}/like")
    public ResponseEntity<BoardLikeResponseDto> changeLikeYN(@PathVariable Long boardid, HttpSession session) {

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.changeLikeYN(boardid, loginUser.getId());

        return new ResponseEntity<>(boardLikeResponseDto, HttpStatus.OK);
    }


    @GetMapping("/{boradid}/like")
    public ResponseEntity<BoardLikeResponseDto> findBoardLikeByIdOrElseThrow(@PathVariable Long boradid, HttpSession session) {

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(boardLikeService.findBoardLikeByIdOrElseThrow(boradid, loginUser.getId()), HttpStatus.OK);
    }

}

