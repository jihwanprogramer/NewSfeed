package org.example.newsfeed.comment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.post.entity.Post;
import org.example.newsfeed.user.entity.Users;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    public Comment(Post post, Users user, String content) {
        this.post = post;
        this.user = user;
        this.content = content;

    }

    public void update(String content) {
        this.content = content;
    }
}
