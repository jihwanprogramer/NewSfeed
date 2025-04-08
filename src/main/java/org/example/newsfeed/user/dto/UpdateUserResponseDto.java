package org.example.newsfeed.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateUserResponseDto {

    private final Long id;

    private final String name;

    private final Integer age;

    private final String email;

    private final String newPassword;

    private final LocalDateTime createdAt;

    private final LocalDateTime modifiedAt;

    public UpdateUserResponseDto(Long id, String name, Integer age, String email, String newPassword, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.newPassword = newPassword;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
