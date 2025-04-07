package org.example.newsfeed.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.login.dto.LoginRequestDto;
import org.example.newsfeed.login.dto.LoginResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @PostMapping
    public ResponseEntity<Map<String,String>> login (LoginRequestDto dto, HttpServletRequest request){

        LoginResponseDto responseDto = loginService.login(dto.getEmail(), dto.getPassword());
        Long userId =responseDto.getId();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = memberservice.findUserById(userId);

        session.setAttribute(Const.LOGIN_USER, "loginUser");

        return new ResponseEntity<>(Map.of("message", "로그인에 성공하였습니다."), HttpStatus.OK);
    }




}
