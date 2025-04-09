package org.example.newsfeed.login.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.exception.LoginAuthException;
import org.example.newsfeed.login.dto.LoginResponseDto;
import org.example.newsfeed.login.repository.LoginRepository;
import org.example.newsfeed.user.entity.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final LoginRepository loginRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(String email, String password){

        User users = loginRepository.findUsersByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password, users.getPassword())){
            throw new LoginAuthException("아이디가 없거나 비밀번호가 틀립니다.");
        }

        return new LoginResponseDto(users.getId());
    }




}
