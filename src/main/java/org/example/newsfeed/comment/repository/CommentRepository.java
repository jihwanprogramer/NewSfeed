package org.example.newsfeed.comment.repository;

import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByPost(Post post);
}
