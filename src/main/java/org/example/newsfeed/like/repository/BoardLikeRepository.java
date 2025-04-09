package org.example.newsfeed.like.repository;

import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByBoardAndUserId (Board board, Long userId);

    BoardLike findBoardLikeById(Long id);
}
