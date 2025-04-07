package org.example.newsfeed.post.repository;


import org.example.newsfeed.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

}
