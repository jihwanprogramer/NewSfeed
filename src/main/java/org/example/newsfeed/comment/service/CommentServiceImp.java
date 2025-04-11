package org.example.newsfeed.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentPageResponseDto;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.exception.MisMatchUserException;
import org.example.newsfeed.user.entity.User;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    /**
     * 새로운 댓글을 생성합니다.
     *
     * @param userId 댓글 작성자 ID
     * @param boardId 댓글이 속하는 게시판 ID
     * @param dto 댓글 저장 요청 DTO
     * @return 생성된 댓글 응답 DTO
     */
    @Override
    @Transactional
    public CommentResponseDto createComment(Long userId, Long boardId, CommentSaveRequestDto dto) {
        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);
        User user = userRepository.findUserByIdOrElseThrow(userId);
        Comment comment = Comment.createComment(findBoard, user, dto.getContent());
        commentRepository.save(comment);
        return new CommentResponseDto(comment);
    }

    /**
     * 주어진 게시판 ID에 속하는 댓글 목록을 조회합니다.
     *
     * @param id 게시판 ID
     * @return 해당 게시판에 속하는 댓글 목록
     */
    @Override
    @Transactional
    public List<CommentResponseDto> findBoardByBoardId(Long id) {
        Board board = boardRepository.findByIdOrElseThrow(id);
        List<Comment> comments = commentRepository.findCommentByBoard(board);
        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    /**
     * 댓글을 수정합니다.
     *
     * @param commentId 수정할 댓글 ID
     * @param userId 댓글 작성자 ID
     * @param commentUpdateRequestDto 댓글 수정 요청 DTO
     * @return 수정된 댓글 응답 DTO
     * @throws MisMatchUserException 글 작성자나 댓글 작성자가 아닐 경우
     */
    @Override
    @Transactional
    public CommentResponseDto updateComment(Long commentId, Long userId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        if (!(comment.getUser().getId().equals(userId) || comment.getBoard().getUser().getId().equals(userId))) {
            throw new MisMatchUserException("글 작성자나 댓글 작성자만 수정 가능합니다.");
        }
        comment.updateContent(commentUpdateRequestDto.getContent());
        return new CommentResponseDto(comment);
    }

    /**
     * 댓글을 삭제합니다.
     *
     * @param commentId 삭제할 댓글 ID
     * @param userId 댓글 작성자 ID
     * @throws MisMatchUserException 글 작성자나 댓글 작성자가 아닐 경우
     */
    @Override
    @Transactional
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);
        if (!comment.getUser().getId().equals(userId)) {
            throw new MisMatchUserException("글 작성자나 댓글 작성자만 삭제 가능합니다.");
        }
        commentRepository.delete(comment);
    }

    /**
     * 페이지네이션을 사용하여 댓글 목록을 조회합니다.
     *
     * @param page 페이지 번호
     * @param size 페이지 크기
     * @return 페이지네이션된 댓글 응답 DTO 목록
     */
    @Override
    public Page<CommentPageResponseDto> findPageCommentBySIZE(int page, int size) {
        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage, size, Sort.by("createdAt").descending());
        Page<Comment> commentPage = commentRepository.findAll(pageable);
        return commentPage.map(CommentPageResponseDto::new);
    }
}
