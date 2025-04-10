package org.example.newsfeed.like.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.comment.dto.CommentResponseDto;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;

import org.example.newsfeed.like.entity.BoardLike;
import org.example.newsfeed.like.entity.CommentLike;
import org.example.newsfeed.like.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    private final CommentRepository commentRepository;

    public CommentLikeResponseDto saveLikeYN(Long commentid, Long userid){

            Comment findComment = commentRepository.findByIdOrElseThrow(commentid);

            Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userid);
            if(optionalCommentLike.isPresent()) {
                 throw new AlreadyExistsEsception("이미 좋아요 내역이 존재합니다.");
            }

            CommentLike commentLike = new CommentLike(userid, true);
            commentLike.setComment(findComment);

            commentLikeRepository.save(commentLike);

            return new CommentLikeResponseDto(commentLike.getId(),countLike(findComment), commentLike.isLikeYN());

    }

    @Transactional
    public CommentLikeResponseDto changeLikeYN(Long commentid, Long userid){

        Comment findComment = commentRepository.findByIdOrElseThrow(commentid);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userid);
        if(optionalCommentLike.isEmpty()){
            throw new AlreadyExistsEsception("좋아요 내역이 존재하지 않습니다.");
        }
        optionalCommentLike.get().changeLikeYN();

        CommentLike changedCommentLike = optionalCommentLike.get();

        return new CommentLikeResponseDto(changedCommentLike.getId(), countLike(findComment), changedCommentLike.isLikeYN());
    }

    public CommentLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId){

        Comment findComment = commentRepository.findByIdOrElseThrow(boardId);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, longinUserId);
        if(optionalCommentLike.isEmpty()){
            return new CommentLikeResponseDto(null, countLike(findComment), false);
        } else {
            return new CommentLikeResponseDto(optionalCommentLike.get().getId(), countLike(findComment), optionalCommentLike.get().isLikeYN());
        }

    }

    public int countLike(Comment comment){

        return commentLikeRepository.countByCommentAndLikeYN(comment, true);
    }

}
