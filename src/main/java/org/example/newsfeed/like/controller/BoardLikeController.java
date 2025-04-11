
package org.example.newsfeed.like.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.like.Service.BoardLikeServiceImpl;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardLikeController {
    private final BoardLikeServiceImpl boardLikeService;

     /**
     * 게시물 좋아요를 생성합니다.
     *
     * @param boardid 게시물의 id
     * @return 생성된 좋아요의 DTO, HttpStatus
     *
     **/

    @PostMapping("/{boardid}/like")
    public ResponseEntity<BoardLikeResponseDto> likeYN(@PathVariable Long boardid, HttpSession session) {
        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER); // 오브젝트를 그대로 쓸수 없어서 다운케스팅

        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.saveLikeYN(boardid, loginUser.getId());

        return new ResponseEntity<>(boardLikeResponseDto, HttpStatus.CREATED);
    }

    /**
     * 게시물 좋아요를 true,false로 전환 합니다.
     *
     * @param boardid 게시물의 id
     * @return 변경된 좋아요의 DTO, HttpStatus
     *
     **/
    @PatchMapping("/{boardid}/like")
    public ResponseEntity<BoardLikeResponseDto> changeLikeYN(@PathVariable Long boardid, HttpSession session) {

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        BoardLikeResponseDto boardLikeResponseDto = boardLikeService.changeLikeYN(boardid, loginUser.getId());

        return new ResponseEntity<>(boardLikeResponseDto, HttpStatus.OK);
    }

    /**
     * 게시물 좋아요를 조회합니다.
     *
     * @param boardid 게시물의 id
     * @return 좋아요의 DTO, HttpStatus
     *
     **/
    @GetMapping("/{boardid}/like")
    public ResponseEntity<BoardLikeResponseDto> findBoardLikeByIdOrElseThrow(@PathVariable Long boardid, HttpSession session) {

        UserResponseDto loginUser = (UserResponseDto) session.getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(boardLikeService.findBoardLikeByIdOrElseThrow(boardid, loginUser.getId()), HttpStatus.OK);
    }

}

