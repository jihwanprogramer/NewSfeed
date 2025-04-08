package org.example.newsfeed.login.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private final Long id;

    public LoginResponseDto(Long id) {
        this.id = id;
    }
}
