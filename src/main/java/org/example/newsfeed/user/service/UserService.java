package org.example.newsfeed.user.service;

import org.example.newsfeed.user.dto.UserResponseDto;

public interface UserService {
    UserResponseDto signUp(String name, Integer age, String email, String password);

    UserResponseDto findUserById(Long id);

}
