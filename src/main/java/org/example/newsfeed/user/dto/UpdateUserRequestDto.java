package org.example.newsfeed.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class UpdateUserRequestDto {

    private final String name;

    @Range(min = 15, max = 130)
    private final Integer age;

    @Email
    private final String email;

    @NotBlank
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
