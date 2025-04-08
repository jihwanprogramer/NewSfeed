package org.example.newsfeed.comment.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.dto.CommentSaveRequestDto;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.post.entity.Post;
import org.example.newsfeed.post.repository.PostRepository;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.example.newsfeed.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostRepository postRepository;

    @Transactional
    public CommentResponseDto save(Long postId, CommentSaveRequestDto dto) {

        Post findPost = postRepository.findByIdOrElseThrow(postId);
        Comment comment = new Comment(findPost,dto.getContent());
        commentRepository.save(comment);

        return new CommentResponseDto(
                comment.getId(),
                findPost.getId(),
                comment.getContent(),
                comment.getCreateAt(),
                comment.getUpdatedAt()
        );



    }
}
