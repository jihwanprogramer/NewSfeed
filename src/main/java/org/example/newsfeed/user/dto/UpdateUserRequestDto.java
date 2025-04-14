package org.example.newsfeed.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.validator.constraints.Range;

@Getter
public class UpdateUserRequestDto {

    private final String name;

    @Range(min = 15, max = 130, message = "나이는 15세이상 130세 이하만 가능합니다")
    private final Integer age;

    @Email(message = "이메일 형식으로 작성해주세요")
    private final String email;

    @NotBlank(message = "비밀번호는 필수로 입력하셔야 합니다")
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
