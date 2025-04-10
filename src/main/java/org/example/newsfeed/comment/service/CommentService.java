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
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto save(Long userId, Long boardId, CommentSaveRequestDto dto) {

        Board findBoard = boardRepository.findByIdOrElseThrow(boardId);

        User user = userRepository.findUserByIdOrElseThrow(userId);
        Comment comment = new Comment(findBoard, user, dto.getContent());
        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }

    @Transactional
    public List<CommentResponseDto> findByBoard(Long id) {
        Board board = boardRepository.findByIdOrElseThrow(id);
        List<Comment> comments = commentRepository.findByBoard(board);
        return comments.stream().map(CommentResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, Long userId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!(comment.getUser().getId().equals(userId) || comment.getBoard().getUser().getId().equals(userId))) {
            throw new MisMatchUserException("글 작성자나 댓글 작성자만 수정 가능합니다.");
        }

        comment.update(commentUpdateRequestDto.getContent());
        return new CommentResponseDto(
                comment
        );

    }

    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!comment.getUser().getId().equals(userId)) {
            throw new MisMatchUserException("글 작성자나 댓글 작성자만 삭제 가능합니다.");
        }
        commentRepository.delete(comment);
    }

    public Page<CommentPageResponseDto> findAllPage(int page, int size) {
        int adjustedPage = (page > 0) ? page - 1 : 0;
        PageRequest pageable = PageRequest.of(adjustedPage,size, Sort.by("createdAt").descending());
        Page<Comment> commentPage = commentRepository.findAll(pageable);

        return commentPage.map(comment -> new CommentPageResponseDto(comment));

    }
}
