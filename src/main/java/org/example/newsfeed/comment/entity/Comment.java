package org.example.newsfeed.comment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.post.entity.Post;

@Getter
@Entity
@NoArgsConstructor
public class Comment extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    public Comment(Post post, String content) {
        this.post =post;
        this.content = content;
    }

}
