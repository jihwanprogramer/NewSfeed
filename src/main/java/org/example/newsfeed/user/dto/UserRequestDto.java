package org.example.newsfeed.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class UserRequestDto {

    @NotBlank(message = "이름은 필수로 입력하셔야 합니다")
    private final String name;

    @NotNull(message = "나이는 필수로 입력하셔야 합니다")
    @Range(min = 15, max = 130)
    private final Integer age;

    @NotBlank(message = "이메일은 필수로 입력하셔야 합니다")
    @Email
    private final String email;

    @NotBlank(message = "비밀번호는 필수로 입력하셔야 합니다")
    private final String password;

    @NotBlank(message = "비밀번호 확인은 필수로 입력하셔야 합니다")
    private final String checkPassword;

    public UserRequestDto(String name, Integer age, String email, String password, String checkPassword) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
        this.checkPassword = checkPassword;
    }
}
