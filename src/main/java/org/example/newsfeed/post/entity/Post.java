package org.example.newsfeed.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.post.dto.CreateRequestDto;
import org.example.newsfeed.post.dto.UpdateRequestDto;
import org.example.newsfeed.user.entity.Users;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Getter
@Table(name = "post")
public class Post extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users user;

    public Post() {

    }

    public Post(CreateRequestDto requestDto) {
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
