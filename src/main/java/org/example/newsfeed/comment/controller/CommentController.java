package org.example.newsfeed.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentPageResponseDto;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import org.example.newsfeed.comment.service.CommentServiceImp;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class CommentController {

    private final CommentServiceImp commentServiceImp;

    /**
     * 댓글을 생성합니다.
     *
     * @param loginUser 로그인한 사용자 정보
     * @param board_id 게시판 ID
     * @param commentSaveRequestDto 댓글 저장 요청 DTO
     * @return 생성된 댓글의 DTO
     */
    @PostMapping("/{board_id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(
            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
            @PathVariable Long board_id,
            @Valid @RequestBody CommentSaveRequestDto commentSaveRequestDto
    ) {
        CommentResponseDto save = commentServiceImp.createComment(loginUser.getId(), board_id, commentSaveRequestDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    /**
     * 특정 게시판의 모든 댓글을 조회합니다.
     *
     * @param board_id 게시판 ID
     * @return 댓글 목록의 DTO 리스트
     */
    @GetMapping("/{board_id}/comments")
    public ResponseEntity<List<CommentResponseDto>> findBoardByBoardId(@PathVariable Long board_id) {
        List<CommentResponseDto> commentResponseDtoList = commentServiceImp.findBoardByBoardId(board_id);
        return new ResponseEntity<>(commentResponseDtoList, HttpStatus.OK);
    }

    /**
     * 댓글을 수정합니다.
     *
     * @param loginUser 로그인한 사용자 정보
     * @param comment_id 댓글 ID
     * @param commentUpdateRequestDto 수정 요청 DTO
     * @return 수정된 댓글의 DTO
     */
    @PutMapping("/comments/{comment_id}")
    public ResponseEntity<CommentResponseDto> updateComment(
            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
            @PathVariable Long comment_id,
            @Valid @RequestBody CommentUpdateRequestDto commentUpdateRequestDto) {

        CommentResponseDto updateComment = commentServiceImp.updateComment(comment_id, loginUser.getId(), commentUpdateRequestDto);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    /**
     * 댓글을 삭제합니다.
     *
     * @param loginUser 로그인한 사용자 정보
     * @param comment_id 댓글 ID
     * @return HTTP 상태 코드
     */
    @DeleteMapping("/comments/{comment_id}")
    public ResponseEntity<Void> deleteComment(
            @SessionAttribute(name = "loginUser") UserResponseDto loginUser,
            @PathVariable Long comment_id) {
        commentServiceImp.deleteComment(comment_id, loginUser.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 페이지네이션된 댓글 목록을 조회합니다.
     *
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @return 페이지화된 댓글 DTO
     */
    @GetMapping("/comments/pages")
    public ResponseEntity<Page<CommentPageResponseDto>> findPageCommentBySIZE(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<CommentPageResponseDto> result = commentServiceImp.findPageCommentBySIZE(page, size);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
