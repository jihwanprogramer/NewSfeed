package org.example.newsfeed.user.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.user.dto.UpdateUserResponseDto;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto signUp(String name, Integer age, String email, String password, String checkPassword) {

        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"이미 있는 이메일입니다");
        }

        if (!password.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);

        Users users = new Users(name, age, email, encodedPassword);

        Users savedUser = userRepository.save(users);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(),savedUser.getAge(),savedUser.getCreatedAt(),
                savedUser.getModifiedAt());

    }


    @Override
    public List<UserResponseDto> findUserByName(String name) {

        return userRepository.findUserByName(name).stream()
                .map(UserResponseDto::new).toList();

    }

    @Override
    public UserResponseDto findUserById(Long id) {

        Users findUser = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(), findUser.getCreatedAt(),
                findUser.getModifiedAt());

    }

    @Override
    public UpdateUserResponseDto updateUser(Long id, String name, Integer age, String email, String password,
                                            String newPassword, String checkNewPassword) {

        Users findUser = userRepository.findUserByIdOrElseThrow(id);

        if (name != null) {
            findUser.setName(name);
        }

        if (age != null) {
            findUser.setAge(age);
        }

        if (email != null) {
            findUser.setEmail(email);
        }

        // 셋 다 넣지 않으면 변경 불가
        if (password != null && newPassword != null && checkNewPassword != null) {

            if (!passwordEncoder.matches(password,findUser.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"원래의 비밀번호가 일치하지 않습니다.");
            }

            if (!newPassword.equals(checkNewPassword)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "새로운 비밀번호와 새로운 비밀번호 확인이 일치하지 않습니다.");
            }
            findUser.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(findUser);

        return new UpdateUserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(),
                findUser.getEmail(),newPassword,findUser.getCreatedAt(),findUser.getModifiedAt());

    }

    @Override
    public void deleteUser(Long id, String password) {

        Users findUser = userRepository.findUserByIdOrElseThrow(id);

        if (!passwordEncoder.matches(password,findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"원래의 비밀번호가 일치하지 않습니다.");
        }
        userRepository.delete(findUser);
    }
}
