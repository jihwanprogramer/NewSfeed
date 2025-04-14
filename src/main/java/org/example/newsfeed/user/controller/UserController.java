package org.example.newsfeed.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
import org.example.newsfeed.user.dto.UpdateUserRequestDto;
import org.example.newsfeed.user.dto.UserRequestDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 회원 가입 요청을 처리합니다.
     *
     * @param userRequestDto 회원 가입 요청 데이터 (이름, 나이, 이메일, 비밀번호 등)
     * @return 생성된 사용자 정보 DTO
     */
    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signUp(@Valid @RequestBody UserRequestDto userRequestDto) {
        UserResponseDto userResponseDto = userService.signUp(
                userRequestDto.getName(),
                userRequestDto.getAge(),
                userRequestDto.getEmail(),
                userRequestDto.getPassword(),
                userRequestDto.getCheckPassword()
        );
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    /**
     * 이름으로 사용자 목록을 조회합니다. (리스트 형태)
     *
     * @param name 검색할 사용자 이름
     * @return 이름에 해당하는 사용자 목록
     */
    @GetMapping
    public ResponseEntity<List<UserResponseDto>> findUserByName(@RequestParam("name") String name) {
        List<UserResponseDto> userResponseDtoList = userService.findUserByName(name);
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    /**
     * 이름으로 사용자 목록을 페이지 형태로 조회합니다.
     *
     * @param name 검색할 사용자 이름
     * @param pageable 페이지 및 정렬 정보
     * @return 이름에 해당하는 사용자 페이지 객체
     */
    @GetMapping("/pages")
    public ResponseEntity<Page<UserResponseDto>> findPageUserByName(
            @RequestParam("name") String name,
            @PageableDefault Pageable pageable
    ) {
        Page<UserResponseDto> userResponseDtoPage = userService.findPageUserByName(name, pageable);
        return new ResponseEntity<>(userResponseDtoPage, HttpStatus.OK);
    }

    /**
     * 사용자 ID로 사용자 정보를 조회합니다.
     *
     * @param id 조회할 사용자 ID
     * @return 해당 ID를 가진 사용자 정보
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable Long id) {
        UserResponseDto findUser = userService.findUserById(id);
        return new ResponseEntity<>(findUser, HttpStatus.OK);
    }

    /**
     * 사용자 정보를 수정합니다.
     *
     * @param id 수정 대상 사용자 ID
     * @param updateUserRequestDto 수정할 정보가 담긴 DTO
     * @param loginUser 세션에 저장된 로그인 사용자 정보
     * @return 수정된 사용자 정보 DTO
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto,
            @SessionAttribute(Const.LOGIN_USER) UserResponseDto loginUser
    ) {
        UserResponseDto updatedUser = userService.updateUser(
                loginUser.getId(),
                id,
                updateUserRequestDto.getName(),
                updateUserRequestDto.getAge(),
                updateUserRequestDto.getEmail(),
                updateUserRequestDto.getPassword(),
                updateUserRequestDto.getNewPassword(),
                updateUserRequestDto.getCheckNewPassword()
        );
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    /**
     * 사용자 계정을 삭제합니다.
     *
     * @param id 삭제할 사용자 ID
     * @param password 비밀번호 확인용
     * @param loginUser 세션에 저장된 로그인 사용자 정보
     * @return 상태 코드 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable Long id,
            @RequestParam("password") String password,
            @SessionAttribute(Const.LOGIN_USER) UserResponseDto loginUser
    ) {
        userService.deleteUser(loginUser.getId(), id, password);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
