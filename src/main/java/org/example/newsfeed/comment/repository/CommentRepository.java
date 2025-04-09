package org.example.newsfeed.comment.repository;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.exception.CommentFindByIdException;
import org.example.newsfeed.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost(Post post);

    default Comment findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() ->
                new CommentFindByIdException("존재하지 않는 댓글 ID 입니다")
        );
    }
}
