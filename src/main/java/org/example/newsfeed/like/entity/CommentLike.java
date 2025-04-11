package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.common.entity.BaseEntity;

@Getter
@Entity
@Table(name = "commentLike")
public class CommentLike extends BaseEntity {

    /**
     *  게시물 좋아요 식별자
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    /**
     * 댓글 좋아요 여부 true false
     */
    @Column(nullable = false)
    private boolean likeYN;

    /**
     * 좋아요가 속해있는 댓글
     */
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    /**
     * 유저 아이디 값
     */
    private Long userId;

    /**
     * 기본 생성자
     */
    public CommentLike() {

    }

    /**
     * 댓글 좋아요 객체 생성자
     */
    public CommentLike(Long userId, boolean likeYN) {

        this.userId = userId;
        this.likeYN = likeYN;

    }

    /**
     * 댓글 초기화
     */
    private void initComment(Comment comment){
        this.comment = comment;
    }

    /**
     * CommentLike 객체를 생성하는 정적 팩토리 메서드
     * @param comment 댓글
     * @param userId 좋아요를 한 userID
     * @return 생성된 CommentLike 객체
     */
    public static CommentLike createLikeYN(Comment comment, Long userId) {

        CommentLike commentLike = new CommentLike(userId, true);
        commentLike.initComment(comment);
        return  commentLike;

    }

    /**
     * PatchMapping에서 댓글 좋아요 true,false 변환
     */
    public boolean changeLikeYN(){
        this.likeYN = !this.likeYN;
        return this.likeYN;
    }
}


