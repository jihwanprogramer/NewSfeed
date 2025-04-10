package org.example.newsfeed.comment.repository;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.exception.CommentFindByIdException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBoard(Board board);

    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new CommentFindByIdException("존재하지 않는 댓글입니다.")
        );
    }
}
