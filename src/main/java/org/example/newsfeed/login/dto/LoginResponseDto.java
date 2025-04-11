package org.example.newsfeed.login.dto;

import lombok.Getter;
/**
 * 로그인 성공 시 반환되는 응답 DTO입니다.
 */
@Getter
public class LoginResponseDto {

    private final Long id;

    /**
     * 로그인 응답 DTO 생성자입니다.
     *
     * @param id 사용자 ID
     */
    public LoginResponseDto(Long id) {
        this.id = id;
    }
}
