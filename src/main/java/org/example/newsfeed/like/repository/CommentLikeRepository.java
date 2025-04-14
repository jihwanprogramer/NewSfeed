package org.example.newsfeed.like.repository;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.like.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long> {
    Optional<CommentLike> findByCommentAndUserId (Comment comment, Long userId);

    int countByCommentAndLikeYN(Comment comment, boolean likeYN);
}

