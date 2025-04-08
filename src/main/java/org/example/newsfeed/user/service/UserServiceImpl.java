package org.example.newsfeed.user.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponseDto signUp(String name, Integer age, String email, String password) {

        Users users = new Users(name, age, email, password);

        Users savedUser = userRepository.save(users);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(),savedUser.getAge(),savedUser.getCreatedAt(),
                savedUser.getModifiedAt());

    }




    @Override
    public UserResponseDto findUserById(Long id) {

        Users findUser = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "유저가 없습니다"));

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(), findUser.getCreatedAt(),
                findUser.getModifiedAt());

    }


}
