package org.example.newsfeed.comment.service;

import org.example.newsfeed.comment.dto.CommentPageResponseDto;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    /**
     * 새로운 댓글을 생성합니다.
     *
     * @param userId 댓글 작성자 ID
     * @param boardId 댓글이 속하는 게시판 ID
     * @param dto 댓글 저장 요청 DTO
     * @return 생성된 댓글 응답 DTO
     */
    CommentResponseDto createComment(Long userId, Long boardId, CommentSaveRequestDto dto);

    /**
     * 주어진 게시판 ID에 속하는 댓글 목록을 조회합니다.
     *
     * @param id 게시판 ID
     * @return 해당 게시판에 속하는 댓글 목록
     */
    List<CommentResponseDto> findBoardByBoardId(Long id);

    /**
     * 댓글을 수정합니다.
     *
     * @param commentId 수정할 댓글 ID
     * @param userId 댓글 작성자 ID
     * @param commentUpdateRequestDto 댓글 수정 요청 DTO
     * @return 수정된 댓글 응답 DTO
     */
    CommentResponseDto updateComment(Long commentId, Long userId, CommentUpdateRequestDto commentUpdateRequestDto);

    /**
     * 댓글을 삭제합니다.
     *
     * @param commentId 삭제할 댓글 ID
     * @param userId 댓글 작성자 ID
     */
    void deleteComment(Long commentId, Long userId);

    /**
     * 페이지네이션을 사용하여 댓글 목록을 조회합니다.
     *
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @return 페이지네이션된 댓글 응답 DTO 목록
     */
    Page<CommentPageResponseDto> findPageCommentBySIZE(int page, int size);
}
