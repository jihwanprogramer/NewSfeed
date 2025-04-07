package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
@Talbe(name = "boardLike")
public class BoardLike extends BaseEntity {
    // BaseEntity를 상속 받아 작성일과 수정일 생성.
    @Id
    @GeneratedValue(Strategy = GenerationType.IDENTITY)
    private Long boardLikeId;

    //튜터님한테 변수명 물어보기. 원시형을 써야하나 ?
    @Column(nullable = false)
    private boolean checkLike;

    @ManyToOne
    @JoinColumn(name = "")
    private Long boardId;

    @ManyToOne
    @JoinColumn(name = "")
    private Long userId;

    public BoardLike(Long boardLikeId, Boolean checkLike, Long boardId, Long userId) {
        this.boardLikeId = boardLikeId;
        this.checkLike = checkLike;
        this.boardId = boardId;
        this.userId = userId;
    }
}
