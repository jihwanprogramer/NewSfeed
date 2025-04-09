package org.example.newsfeed.board.repository;


import org.example.newsfeed.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    //아이디 확인 후 존재하지 않을 경우 예외
    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다.")
        );
    }
}
