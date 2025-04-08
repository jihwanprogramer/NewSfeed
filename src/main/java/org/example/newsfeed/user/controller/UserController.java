package org.example.newsfeed.user.controller;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.user.dto.UpdateUserResponseDto;
import org.example.newsfeed.user.dto.UserRequestDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.service.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDto> signUp(@RequestBody UserRequestDto userRequestDto) {

        UserResponseDto userResponseDto = userService.signUp(userRequestDto.getName(), userRequestDto.getAge(),
                userRequestDto.getEmail(), userRequestDto.getPassword());

        return new ResponseEntity<>(userResponseDto,HttpStatus.CREATED);

    }
    // 미완성 상태 다시 해야 함
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUserByName(@RequestParam("name") String name) {

        List<UserResponseDto> userResponseDtoList = userService.findUserByName(name);

        return new ResponseEntity<>(userResponseDtoList,HttpStatus.OK);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {

        UserResponseDto findUser = userService.findUserById(id);

        return new ResponseEntity<>(findUser,HttpStatus.OK);
    }

//    @PatchMapping("/{id}")
//    public ResponseEntity<UpdateUserResponseDto> updateUser(@PathVariable Long id) {
//
//        userService.
//    }
}
