package org.example.newsfeed.login.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
/**
 * 로그인 요청 시 사용되는 DTO입니다.
 */
@Getter
public class LoginRequestDto {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    /**
     * 로그인 요청 DTO 생성자입니다.
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     */
    public LoginRequestDto(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
