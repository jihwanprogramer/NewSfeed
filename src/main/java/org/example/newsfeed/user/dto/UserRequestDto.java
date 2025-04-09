package org.example.newsfeed.user.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {

    private final String name;

    private final Integer age;

    private final String email;

    private final String password;

    private final String checkPassword;

    public UserRequestDto(String name, Integer age, String email, String password, String checkPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }
}
