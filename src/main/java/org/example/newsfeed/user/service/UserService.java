package org.example.newsfeed.user.service;

import org.example.newsfeed.user.dto.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    UserResponseDto signUp(String name, Integer age, String email, String password, String checkPassWord);

    List<UserResponseDto> findUserByName(String name);

    Page<UserResponseDto> findUserByNamePage(String name, Pageable pageable);

    UserResponseDto findUserById(Long id);

    UserResponseDto updateUser(Long loginUserId, Long id, String name, Integer age,
                                     String email, String password, String newPassword, String checkNewPassword);

    void deleteUser(Long loginUserId, Long id, String password);
}
