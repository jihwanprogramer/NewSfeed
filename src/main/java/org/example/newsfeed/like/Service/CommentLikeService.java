package org.example.newsfeed.like.Service;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;

public interface CommentLikeService {

    /**
     * 좋아요 정보 생성
     * @param commentid 댓글 ID
     * @param userid 좋아요 유저 ID
     * @return 생성된 댓글 좋아요 정보 DTO
     */
    CommentLikeResponseDto saveLikeYN(Long commentid, Long userid);

    /**
     *  좋아요 여부 변경 true, false
     * @param commentid 댓글 ID
     * @param userid 좋아요 유저 ID
     * @return 변경된 댓글 좋아요 정보 DTO
     */
    CommentLikeResponseDto changeLikeYN(Long commentid, Long userid);

    /**
     *  로그인한 유저의 좋아요 찾기
     * @param commentId 댓글 ID
     * @param longinUserId 로그인한 유저 ID
     * @return 조회된 게시물 좋아요 정보 DTO
     */
    CommentLikeResponseDto findCommentLikeByIdOrElseThrow(Long commentId, Long longinUserId);

    /**
     *  좋아요 갯수 합산
     * @param comment 댓글 정보
     * @return 좋아요 수
     */
    int countLike(Comment comment);
}
