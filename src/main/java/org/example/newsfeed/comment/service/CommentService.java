package org.example.newsfeed.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.dto.CommentUpdateRequestDto;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.exception.UnauthorizedCommentAccessException;
import org.example.newsfeed.post.entity.Post;
import org.example.newsfeed.post.repository.PostRepository;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponseDto save(Long userId, Long postId, CommentSaveRequestDto dto) {

        Post findPost = postRepository.findByIdOrElseThrow(postId);

        Users user = userRepository.findUserByIdOrElseThrow(userId);
        Comment comment = new Comment(findPost, user, dto.getContent());
        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                findPost.getId(),
                user.getId(),
                comment.getContent(),
                comment.getCreateAt(),
                comment.getUpdatedAt()
        );

    }

    @Transactional
    public List<CommentResponseDto> findByPost(Long id) {
        Post post = postRepository.findByIdOrElseThrow(id);
        List<Comment> comments = commentRepository.findByPost(post);

        return comments.stream().map(comment -> new CommentResponseDto(
                comment.getId(),
                post.getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getCreateAt(),
                comment.getUpdatedAt()
        )).collect(Collectors.toList());
    }

    @Transactional
    public CommentResponseDto updateComment(Long commentId, Long userId, CommentUpdateRequestDto commentUpdateRequestDto) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!(comment.getUser().getId().equals(userId) || comment.getPost().getUser().getId().equals(userId))) {
            throw new UnauthorizedCommentAccessException("게시물 또는 댓글을 작성한 회원만 업데이트 가능합니다.");
        }

        comment.update(commentUpdateRequestDto.getContent());

        return new CommentResponseDto(
                comment.getId(),
                comment.getPost().getId(),
                comment.getUser().getId(),
                comment.getContent(),
                comment.getCreateAt(),
                comment.getUpdatedAt()
        );

    }

    @Transactional
    public void delete(Long commentId, Long userId) {
        Comment comment = commentRepository.findByIdOrElseThrow(commentId);

        if (!(comment.getUser().getId().equals(userId) || comment.getPost().getUser().getId().equals(userId))) {
            throw new UnauthorizedCommentAccessException("게시물 또는 댓글을 작성한 회원만 삭제 가능합니다.");
        }
        commentRepository.delete(comment);
    }
}
