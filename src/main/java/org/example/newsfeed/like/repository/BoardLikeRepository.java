package org.example.newsfeed.like.repository;

import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.like.dto.BoardLikeResponseDto;
import org.example.newsfeed.like.entity.BoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface BoardLikeRepository extends JpaRepository<BoardLike, Long> {
    Optional<BoardLike> findByBoardAndUserId (Board board, Long userId);


    default BoardLike findBoardLikeByIdOrElseThrow (Long id){
        return findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않은 좋아요 입니다."));
    }

    int countByBoardAndLikeYN(Board board, boolean likeYN);
}
