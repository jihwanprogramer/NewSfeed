package org.example.newsfeed.comment.repository;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.exception.NullResponseException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 주어진 게시판에 속하는 댓글 목록을 찾습니다.
     *
     * @param board 댓글을 찾을 게시판
     * @return 해당 게시판에 속하는 댓글 목록
     */
    List<Comment> findCommentByBoard(Board board);

    /**
     * 주어진 ID에 해당하는 댓글을 찾고, 존재하지 않을 경우 예외를 발생시킵니다.
     *
     * @param id 댓글 ID
     * @return 댓글 객체
     * @throws NullResponseException 존재하지 않는 댓글일 경우
     */
    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new NullResponseException("존재하지 않는 댓글입니다.")
        );
    }
}
