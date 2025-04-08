package org.example.newsfeed.login.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.exception.LoginAuthException;
import org.example.newsfeed.login.dto.LoginResponseDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(String email, String password){

        User user = userRepository.findUserByEmailOrElseThrow(email);

        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new LoginAuthException("아이디가 없거나 비밀번호가 틀립니다.");
        }

        return new LoginResponseDto(user.getId(Id));
    }




}
