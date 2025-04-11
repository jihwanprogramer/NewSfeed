package org.example.newsfeed.board.repository;


import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.exception.MisMatchUserException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface BoardRepository extends JpaRepository<Board, Long> {

    Page<Board> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate, PageRequest pageable);

    //아이디 확인 후 존재하지 않을 경우 예외
    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new MisMatchUserException("존재하지 않는 아이디입니다.")
        );
    }

}
