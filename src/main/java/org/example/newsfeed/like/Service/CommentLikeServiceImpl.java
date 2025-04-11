package org.example.newsfeed.like.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.comment.repository.CommentRepository;
import org.example.newsfeed.exception.AlreadyExistsException;
import org.example.newsfeed.exception.NullResponseException;
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

    /**
     *  댓글 좋아요 생성
     * @param commentId 댓글 ID
     * @param userId 좋아요 유저 ID
     * @return 생성된 댓글 좋아요 DTO
     * @throws AlreadyExistsException 이미 좋아요가 존재할 경우 예외 발생
     */
    @Override
    public CommentLikeResponseDto saveLikeYN(Long commentId, Long userId){

            Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

            Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userId);
            if(optionalCommentLike.isPresent()) {
                 throw new AlreadyExistsException("이미 좋아요 내역이 존재합니다.");
            }

            CommentLike commentLike = CommentLike.createLikeYN(findComment, userId);

            commentLikeRepository.save(commentLike);

            return new CommentLikeResponseDto(commentLike.getId(),countLike(findComment), commentLike.isLikeYN());

    }

    /**
     * 좋아요 상태를 토글
     * @param commentId 댓글 ID
     * @param userId 좋아요 유저 ID
     * @return 좋아요의 상태 true, false
     * @throws NullResponseException 좋아요 내역이 없다면 발생하는 예외
     */
    @Transactional
    @Override
    public CommentLikeResponseDto changeLikeYN(Long commentId, Long userId){

        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, userId);
        if(optionalCommentLike.isEmpty()){
            throw new AlreadyExistsException("좋아요 내역이 존재하지 않습니다.");
        }
        optionalCommentLike.get().changeLikeYN();

        CommentLike changedCommentLike = optionalCommentLike.get();

        return new CommentLikeResponseDto(changedCommentLike.getId(), countLike(findComment), changedCommentLike.isLikeYN());
    }

    /**
     * 좋아요 내역 조회 하기
     * @param commentId 댓글 ID
     * @param longinUserId 로그인한 유저 ID
     * @return 좋아요 확인여부의 정보 DTO
     */
    @Override
    public CommentLikeResponseDto findCommentLikeByIdOrElseThrow(Long commentId, Long longinUserId){

        Comment findComment = commentRepository.findByIdOrElseThrow(commentId);

        Optional<CommentLike> optionalCommentLike = commentLikeRepository.findByCommentAndUserId(findComment, longinUserId);
        if(optionalCommentLike.isEmpty()){
            return new CommentLikeResponseDto(null, countLike(findComment), false);
        } else {
            return new CommentLikeResponseDto(optionalCommentLike.get().getId(), countLike(findComment), optionalCommentLike.get().isLikeYN());
        }

    }

    /**
     * 댓글의 좋아요 총 갯수
     * @param comment 댓글 정보
     * @return 좋아요 갯수
     */
    @Override
    public int countLike(Comment comment){

        return commentLikeRepository.countByCommentAndLikeYN(comment, true);
    }

}
