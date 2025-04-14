package org.example.newsfeed.user.dto;

import lombok.Getter;
import org.example.newsfeed.user.entity.User;

import java.time.LocalDateTime;

@Getter
public class UserResponseDto {

    private final Long id;

    private final String name;

    private final Integer age;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UserResponseDto(Long id, String name, Integer age, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public UserResponseDto(User users) {
        this(users.getId(), users.getName(), users.getAge(), users.getCreatedAt(),users.getModifiedAt());
    }
}
