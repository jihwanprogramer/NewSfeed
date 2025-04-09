package org.example.newsfeed.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.newsfeed.board.dto.CreateRequestDto;
import org.example.newsfeed.board.dto.UpdateRequestDto;
import org.example.newsfeed.user.entity.User;

@Entity
@Getter
@Table(name = "post")
public class Board extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
}
