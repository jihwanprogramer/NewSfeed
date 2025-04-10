package org.example.newsfeed.like.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.like.Service.BoardLikeService;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.like.entity.BoardLike;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardLikeController {
    private final BoardLikeService boardLikeService;

    @PostMapping("/{boardid}/like")
    public ResponseEntity<BoardLikeResponseDto> likeYN(@PathVariable Long boardid, HttpSession session){
        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER); // 오브젝트를 그대로 쓸수 없어서 다운케스팅

        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.saveLikeYN(boardid, loginUser.getId());

        return new ResponseEntity<>(boardLikeResponseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{boardid}/like")
    public ResponseEntity<Map<String,String>> changeLikeYN(@PathVariable Long boardid, HttpSession session){

        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER);

        boolean likeYN = boardLikeService.changeLikeYN(boardid, loginUser.getId());

        if(likeYN){
            return new ResponseEntity<>(Map.of("message", "다시 좋아요 하였습니다."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("message", "좋아요를 취소하였습니다."), HttpStatus.OK);
        }
    }

//    @GetMapping
//    public ResponseEntity<List<BoardLikeResponseDto>> findAllBoardLike(){
//
//        return new ResponseEntity<>(boardLikeService.findAllBoardLike(),HttpStatus.OK);
//    }

//    @GetMapping("{boradid}/like/{likeid}")
//    public ResponseEntity<BoardLikeResponseDto> findByBoardLikeId(@PathVariable Long id){
//
//        return new ResponseEntity<>(boardLikeService.findBoardLikeById(id),HttpStatus.OK);
//    }
}
