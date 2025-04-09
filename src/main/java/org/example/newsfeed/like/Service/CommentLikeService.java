package org.example.newsfeed.like.Service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;

import org.example.newsfeed.like.entity.CommentLike;
import org.example.newsfeed.like.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    private final CommentRepository commentRepository;

    public CommentLikeResponseDto saveLikeYN(Long commentId, Long userId){

            Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

            Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userId);
            if(optionalCommentLike.isPresent()) {
                 throw new AlreadyExistsEsception("이미 좋아요 내역이 존재합니다.");
            }

            CommentLike commentLike = new CommentLike(userId, true);
            commentLike.setComment(findComment);

            commentLikeRepository.save(commentLike);

            return new CommentLikeResponseDto(commentLike.getId(), commentLike.isLikeYN());

    }

    public boolean changeLikeYN(Long commentId, Long userId){

        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userId);
        if(optionalCommentLike.isEmpty()){
            throw new AlreadyExistsEsception("좋아요 내역이 존재하지 않습니다.");
        }

        return optionalCommentLike.get().changeLikeYN();
    }


}
