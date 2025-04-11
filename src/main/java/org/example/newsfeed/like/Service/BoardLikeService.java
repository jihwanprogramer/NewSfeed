package org.example.newsfeed.like.Service;

import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;

public interface BoardLikeService {

    BoardLikeResponseDto saveLikeYN(Long boardId, Long userId);
    BoardLikeResponseDto changeLikeYN(Long boardId, Long userId);
    BoardLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId);
    int countLike(Board board);
}
