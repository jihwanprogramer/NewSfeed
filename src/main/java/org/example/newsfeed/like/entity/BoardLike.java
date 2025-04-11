package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.board.entity.Board;
import org.example.newsfeed.common.entity.BaseEntity;

@Getter
@Entity
@Table(name = "boardLike")
public class BoardLike extends BaseEntity {
    // BaseEntity를 상속 받아 작성일과 수정일 생성.

    /**
     * 게시물 좋아요 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 겜시물 좋아요 여부 true false.
     */
    @Column(nullable = false)
    private boolean likeYN;

    /**
     * 좋아요가 속해있는 게시글.
     */
    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    /**
     * 유저 아이디 값.
     */
    @Column(nullable = false)
    private Long userId;

    /**
     * 기본 생성자.
     */
    public BoardLike() {
    }

    /**
     *게시물 좋아요 객체 생성자.
     */
    public BoardLike(Long userId, boolean likeYN) {

        this.userId = userId;
        this.likeYN = likeYN;

    }

    /**
     * 보드 초기화
     */
    public void setBoard(Board board){
        this.board = board;
    }

    /**
     * PatchMapping에서 게시물 좋아요 true,false 변환.
     */
    public boolean changeLikeYN(){
    this.likeYN = !this.likeYN;
        return this.likeYN;
    }


}
