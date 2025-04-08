package org.example.newsfeed.user.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UserRequestDto {

    private final String name;

    private final Integer age;

    private final String email;

    private final String password;

    public UserRequestDto(String name, Integer age, String email, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}
