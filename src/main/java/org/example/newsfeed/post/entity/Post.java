package org.example.newsfeed.post.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.post.dto.CreateRequestDto;

@Entity
@Getter
@Table(name = "post")
public class Post extends TimeEntity {

    //유저 객체 추가

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    public Post() {

    }

    public Post(CreateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
    }
}
