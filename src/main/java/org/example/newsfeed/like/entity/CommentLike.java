package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.comment.entity.Comment;

@Getter
@Entity
@Table(name = "commentLike")
public class CommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //튜터님한테 변수명 물어보기. 원시형을 써야하나 ?
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


