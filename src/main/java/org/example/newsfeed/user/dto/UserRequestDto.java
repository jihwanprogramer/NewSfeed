package org.example.newsfeed.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class UserRequestDto {

    @NotBlank
    private final String name;

    @NotNull
    @Range(min = 15, max = 130)
    private final Integer age;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    private final String checkPassword;

    public UserRequestDto(String name, Integer age, String email, String password, String checkPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }
}
