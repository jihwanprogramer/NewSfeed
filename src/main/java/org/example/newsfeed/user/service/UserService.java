package org.example.newsfeed.user.service;

import org.example.newsfeed.user.dto.UpdateUserResponseDto;
import org.example.newsfeed.user.dto.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto signUp(String name, Integer age, String email, String password, String checkPassWord);

    List<UserResponseDto> findUserByName(String name);

    UserResponseDto findUserById(Long id);

    UpdateUserResponseDto updateUser(Long id, String name, Integer age, String email, String password,
                                     String newPassword, String checkNewPassword);
    void deleteUser(Long id);
}
