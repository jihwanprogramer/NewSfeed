package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Table(name = "commentLike")
public class CommentLike {
    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long commentLikeId;

    //튜터님한테 변수명 물어보기. 원시형을 써야하나 ?
    @Column(nullable = false)
    private boolean likeYN;

    @ManyToOne
    @JoinColumn(name = "")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "")
    private Long userId;

    public CommentLike(Long commentLikeId, boolean likeYN, Long commentId, Long userId) {
        this.commentLikeId = commentLikeId;
        this.likeYN = likeYN;
        this.commentId = commentId;
        this.userId = userId;
    }
}


