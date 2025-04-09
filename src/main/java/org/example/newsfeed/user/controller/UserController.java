package org.example.newsfeed.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.user.dto.UpdateUserRequestDto;
import org.example.newsfeed.user.dto.UpdateUserResponseDto;
import org.example.newsfeed.user.dto.UserRequestDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.signUp(userRequestDto.getName(), userRequestDto.getAge(),
                userRequestDto.getEmail(), userRequestDto.getPassword(),userRequestDto.getCheckPassword());

        return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);

    }

    // 이름으로 유저들 조회
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUserByName(@RequestParam("name") String name) {

        List<UserResponseDto> userResponseDtoList = userService.findUserByName(name);

        return new ResponseEntity<>(userResponseDtoList,HttpStatus.OK);
        
    }

    // 아이디로 유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {

        UserResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }

    // 유저 수정
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable Long id,
                                                            @RequestBody UpdateUserRequestDto updateUserRequestDto) {

        UpdateUserResponseDto updateUserResponseDto = userService.updateUser(id, updateUserRequestDto.getName(),
                updateUserRequestDto.getAge(), updateUserRequestDto.getEmail(), updateUserRequestDto.getPassword(),
                updateUserRequestDto.getNewPassword(), updateUserRequestDto.getCheckNewPassword());

        return new ResponseEntity<>(updateUserResponseDto,HttpStatus.OK);
    }

    // 유저 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id, @RequestParam("password") String password) {

        userService.deleteUser(id, password);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
