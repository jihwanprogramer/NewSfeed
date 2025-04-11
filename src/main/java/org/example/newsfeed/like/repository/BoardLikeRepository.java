package org.example.newsfeed.like.repository;

import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    /**
     * 게시물 좋아요 요청자 ID와 게시물 정보를 조회
     *
     * @param board 게시물
     * @param userId 게시물에 좋아요한 유저ID
     * @return 좋아요 정보 Optional
     */
    Optional<BoardLike> findByBoardAndUserId (Board board, Long userId);

    /**
     * 좋아요 합 계산
     * @param board 게시물
     * @param likeYN 좋아요 여부
     * @return 해당 게시물에 좋아요 합
     */
    int countByBoardAndLikeYN(Board board, boolean likeYN);

    void deleteAllByBoard(Board board);
}
