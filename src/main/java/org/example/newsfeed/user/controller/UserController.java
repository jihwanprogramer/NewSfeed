package org.example.newsfeed.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.user.dto.UserRequestDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.signUp(userRequestDto.getName(), userRequestDto.getAge(), userRequestDto.getEmail(),
                userRequestDto.getPassword());

        return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);

    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {

        UserResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }

}
