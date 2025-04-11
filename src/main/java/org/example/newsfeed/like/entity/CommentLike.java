package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.common.entity.BaseEntity;

@Getter
@Entity
@Table(name = "commentLike")
public class CommentLike extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private boolean likeYN;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    private Long userId;

    public CommentLike() {

    }

    public CommentLike(Long userId, boolean likeYN) {

        this.userId = userId;
        this.likeYN = likeYN;

    }

    public void setComment(Comment comment){
        this.comment = comment;
    }

    public boolean changeLikeYN(){
        this.likeYN = !this.likeYN;
        return this.likeYN;
    }
}


