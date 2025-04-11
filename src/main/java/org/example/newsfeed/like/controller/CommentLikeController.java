package org.example.newsfeed.like.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.like.Service.CommentLikeServiceImpl;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentLikeController {
    private final CommentLikeServiceImpl commentLikeService;


    /**
     * 댓글 좋아요를 생성합니다.
     *
     * @param commentid 댓글의 id
     * @return 생성된 좋아요의 DTO, HttpStatus
     *
     **/
    @PostMapping("/{commentid}/like")
    public ResponseEntity<CommentLikeResponseDto> likeYN(@PathVariable Long commentid, HttpSession session){
        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER) ;

        CommentLikeResponseDto commentLikeResponseDto = commentLikeService.saveLikeYN(commentid, loginUser.getId());

        return new ResponseEntity<>(commentLikeResponseDto, HttpStatus.CREATED);
    }


    /**
     * 댓글 좋아요를 true,false로 전환 합니다.
     *
     * @param commentid 댓글의 id
     * @return 변경된 좋아요의 DTO, HttpStatus
     *
     **/
    @PatchMapping("/{commentid}/like")
    public ResponseEntity<CommentLikeResponseDto> changeLikeYN(@PathVariable Long commentid, HttpSession session){
        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER);

        CommentLikeResponseDto commentLikeResponseDto = commentLikeService.changeLikeYN(commentid, loginUser.getId());

       return new ResponseEntity<>(commentLikeResponseDto, HttpStatus.OK);
    }

    /**
     * 댓글 좋아요를 조회합니다.
     *
     * @param commentid 댓글의 id
     * @return 좋아요의 DTO, HttpStatus
     *
     **/
    @GetMapping("/{commentid}/like")
    public ResponseEntity<CommentLikeResponseDto> findBoardLikeByIdOrElseThrow(@PathVariable Long commentid, HttpSession session){

        UserResponseDto loginUser = (UserResponseDto)session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(commentLikeService.findBoardLikeByIdOrElseThrow(commentid, loginUser.getId()),HttpStatus.OK);
    }

}
