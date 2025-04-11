package org.example.newsfeed.like.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.exception.AlreadyExistsException;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;
import org.example.newsfeed.like.entity.CommentLike;
import org.example.newsfeed.like.repository.CommentLikeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class CommentLikeServiceImpl implements CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;

    private final CommentRepository commentRepository;

    @Override
    public CommentLikeResponseDto saveLikeYN(Long commentid, Long userid){

            Comment findComment = commentRepository.findByIdOrElseThrow(commentid);

            Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userid);
            if(optionalCommentLike.isPresent()) {
                 throw new AlreadyExistsException("이미 좋아요 내역이 존재합니다.");
            }

            CommentLike commentLike = new CommentLike(userid, true);
            commentLike.setComment(findComment);

            commentLikeRepository.save(commentLike);

            return new CommentLikeResponseDto(commentLike.getId(),countLike(findComment), commentLike.isLikeYN());

    }

    @Transactional
    @Override
    public CommentLikeResponseDto changeLikeYN(Long commentid, Long userid){

        Comment findComment = commentRepository.findByIdOrElseThrow(commentid);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userid);
        if(optionalCommentLike.isEmpty()){
            throw new AlreadyExistsException("좋아요 내역이 존재하지 않습니다.");
        }
        optionalCommentLike.get().changeLikeYN();

        CommentLike changedCommentLike = optionalCommentLike.get();

        return new CommentLikeResponseDto(changedCommentLike.getId(), countLike(findComment), changedCommentLike.isLikeYN());
    }

    @Override
    public CommentLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId){

        Comment findComment = commentRepository.findByIdOrElseThrow(boardId);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, longinUserId);
        if(optionalCommentLike.isEmpty()){
            return new CommentLikeResponseDto(null, countLike(findComment), false);
        } else {
            return new CommentLikeResponseDto(optionalCommentLike.get().getId(), countLike(findComment), optionalCommentLike.get().isLikeYN());
        }

    }

    @Override
    public int countLike(Comment comment){

        return commentLikeRepository.countByCommentAndLikeYN(comment, true);
    }

}
