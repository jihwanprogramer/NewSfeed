package org.example.newsfeed.login.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.exception.LoginAuthException;
import org.example.newsfeed.login.dto.LoginResponseDto;
import org.example.newsfeed.login.repository.LoginRepository;
import org.example.newsfeed.user.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
/**
 * 로그인 관련 비즈니스 로직을 처리하는 서비스 클래스입니다.
 */
@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 로그인 요청을 처리합니다.
     *
     * @param email 사용자 이메일
     * @param password 사용자 비밀번호
     * @return 로그인 성공 시 사용자 ID를 담은 응답 DTO
     * @throws LoginAuthException 이메일이 없거나 비밀번호가 일치하지 않을 경우 발생
     */
    public LoginResponseDto login(String email, String password) {
        User users = loginRepository.findUsersByEmailOrElseThrow(email);

        if (!passwordEncoder.matches(password, users.getPassword())) {
            throw new LoginAuthException("아이디가 없거나 비밀번호가 틀립니다.");
        }

        return new LoginResponseDto(users.getId());
    }
}
