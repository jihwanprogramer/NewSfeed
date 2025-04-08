package org.example.newsfeed.user.dto;


import lombok.Getter;

@Getter
public class UpdateUserRequestDto {

    private final String name;

    private final Integer age;

    private final String email;

    private final String password;

    private final String newPassword;

    private final String checkNewPassword;


    public UpdateUserRequestDto(String name, Integer age, String email, String password, String newPassword, String checkNewPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.newPassword = newPassword;
        this.checkNewPassword = checkNewPassword;
    }
}
