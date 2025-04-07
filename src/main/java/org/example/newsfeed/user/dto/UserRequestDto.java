package org.example.newsfeed.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    private final Long id;

    private final String name;

    private final Integer age;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;


    public UserRequestDto(Long id, String name, Integer age, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
