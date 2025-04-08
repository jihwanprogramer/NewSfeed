package org.example.newsfeed.post.dto;

import lombok.Getter;
import org.example.newsfeed.post.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {

    private Long id;

    private String title;

    private String contents;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.modifiedAt = post.getModifiedAt();
    }

    public static PostResponseDto findAll(Post post) {
        return new PostResponseDto(post);
    }
}
