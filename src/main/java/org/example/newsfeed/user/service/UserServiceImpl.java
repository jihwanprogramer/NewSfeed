package org.example.newsfeed.user.service;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.Const;
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

    // service) 회원가입
    @Override
    public UserResponseDto signUp(String name, Integer age, String email, String password, String checkPassword) {

        // 중복 이메일 체크
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"이미 있는 이메일입니다");
        }

        // 비밀번호와 체크 비밀번호 일치하는지 확인
        if (!password.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"비밀번호가 일치하지 않습니다.");
        }

        String encodedPassword = passwordEncoder.encode(password);

        Users users = new Users(name, age, email, encodedPassword);

        Users savedUser = userRepository.save(users);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(),savedUser.getAge(),savedUser.getCreatedAt(),
                savedUser.getModifiedAt());

    }

    // service) 이름으로 유저들 조회
    @Override
    public List<UserResponseDto> findUserByName(String name) {

        return userRepository.findUserByName(name).stream()
                .map(UserResponseDto::new).toList();

    }

    // service) 아이디로 유저 조회
    @Override
    public UserResponseDto findUserById(Long id) {

        Users findUser = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(), findUser.getCreatedAt(),
                findUser.getModifiedAt());

    }

    // service) 유저 수정
    @Override
    public UpdateUserResponseDto updateUser(Long id, HttpServletRequest httpServletRequest, String name, Integer age,
                                            String email, String password, String newPassword, String checkNewPassword) {


        UserResponseDto userResponseDto = (UserResponseDto) httpServletRequest.getSession(false)
                .getAttribute(Const.LOGIN_USER);

        if (!userResponseDto.getId().equals(id)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"본인 정보만 수정할 수 있습니다.");
        }

        Users findUser = userRepository.findUserByIdOrElseThrow(id);

        if (name != null) {
            findUser.setName(name);
        }

        if (age != null) {
            findUser.setAge(age);
        }

        // 널은 허용하지만 빈 문자열은 허용하지 않음
        if (email != null && !email.isEmpty()) {
            findUser.setEmail(email);
        }

        // 둘 다 넣지 않으면 변경 불가
        if (newPassword != null && checkNewPassword != null) {

            passwordMatch(password, findUser);

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

    // service) 유저 삭제
    @Override
    public void deleteUser(Long id, String password) {

        Users findUser = userRepository.findUserByIdOrElseThrow(id);

        passwordMatch(password, findUser);

        userRepository.delete(findUser);
    }

    // 패스워드 매치 확인 메서드
    private void passwordMatch(String password, Users users) {
        if (!passwordEncoder.matches(password,users.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"원래의 비밀번호가 일치하지 않습니다.");
        }
    }
}
