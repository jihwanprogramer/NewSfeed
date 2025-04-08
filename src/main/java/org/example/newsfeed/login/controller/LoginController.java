package org.example.newsfeed.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.login.dto.LoginRequestDto;
import org.example.newsfeed.login.dto.LoginResponseDto;
import org.example.newsfeed.login.service.LoginService;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final UserService userService;
    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login ( LoginRequestDto dto, HttpServletRequest request){

        LoginResponseDto responseDto = loginService.login(dto.getEmail(), dto.getPassword());
        Long userId =responseDto.getId();

        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findUserById(userId);

        session.setAttribute(Const.LOGIN_USER, "loginUser");

        return new ResponseEntity<>(Map.of("message", "로그인에 성공하였습니다."), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String,String>> logout (HttpServletRequest request){

        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(Map.of("message", "로그아웃하였습니다."),HttpStatus.OK);
    }




}
