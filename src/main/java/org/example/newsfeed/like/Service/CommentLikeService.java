package org.example.newsfeed.like.Service;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.like.dto.CommentLikeResponseDto;

public interface CommentLikeService {
    CommentLikeResponseDto saveLikeYN(Long commentid, Long userid);
    CommentLikeResponseDto changeLikeYN(Long commentid, Long userid);
    CommentLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId);
    int countLike(Comment comment);
}
