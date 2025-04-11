package org.example.newsfeed.like.Service;

import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;

public interface BoardLikeService {

    /**
     * 좋아요 정보 생성
     * @param boardId 게시물 ID
     * @param userId 좋아요 유저 ID
     * @return 생성된 게시물 좋아요 정보 DTO
     */
    BoardLikeResponseDto saveLikeYN(Long boardId, Long userId);

    /**
     *  좋아요 여부 변경 true, false
     * @param boardId 게시물 ID
     * @param userId 좋아요 유저 ID
     * @return 변경된 게시물 좋아요 정보 DTO
     */
    BoardLikeResponseDto changeLikeYN(Long boardId, Long userId);

    /**
     * 로그인한 유저의 좋아요 찾기
     * @param boardId 게시물 ID
     * @param longinUserId 로그인한 유저 ID
     * @return 조회된 게시물 좋아요 정보 DTO
     */
    BoardLikeResponseDto findBoardLikeByIdOrElseThrow(Long boardId, Long longinUserId);

    /**
     *  좋아요 갯수 합산
     * @param board 게시물 정보
     * @return 좋아요 수
     */
    int countLike(Board board);
}
