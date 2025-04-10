package org.example.newsfeed.like.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.board.entity.Board;

@Getter
@Entity
@Table(name = "boardLike")
public class BoardLike extends BaseEntity {
    // BaseEntity를 상속 받아 작성일과 수정일 생성.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //튜터님한테 변수명 물어보기. 원시형을 써야하나 ?
    @Column(nullable = false)
    private boolean likeYN;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Column(nullable = false)
    private Long userId;

    public BoardLike() {
    }

    public BoardLike(Long userId, boolean likeYN) {

        this.userId = userId;
        this.likeYN = likeYN;

    }

    public void setBoard(Board board){
        this.board = board;
    }

    public boolean changeLikeYN(){
    this.likeYN = !this.likeYN;
    return this.likeYN;
    }


}
