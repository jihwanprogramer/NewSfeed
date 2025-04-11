package org.example.newsfeed.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.newsfeed.board.dto.CreateRequestDto;
import org.example.newsfeed.board.dto.UpdateRequestDto;
import org.example.newsfeed.comment.entity.Comment;
import org.example.newsfeed.user.entity.BaseEntity;
import org.example.newsfeed.user.entity.User;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
    private List<Comment> comments = new ArrayList<>();

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

    public void increaseLike() {
        likesCount++;
    }

    public void decreaseLike() {
        likesCount--;
    }
}
