package org.example.newsfeed.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.newsfeed.board.dto.CreateRequestDto;
import org.example.newsfeed.board.dto.UpdateRequestDto;
import org.example.newsfeed.user.entity.BaseEntity;
import org.example.newsfeed.user.entity.User;

@Entity
@Getter
@Table(name = "boards")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Setter
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private int likesCount;

    public Board() {

    }

    public Board(CreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }

    public void updateTitle(UpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
    }

    public void updateContents(UpdateRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

    public void initBoardLikes(int likesCount) {
        this.likesCount = likesCount;
    }
}
