package org.example.newsfeed.user.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.User;
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

        passwordCheck(password,checkPassword);

        String encodedPassword = passwordEncoder.encode(password);

        User users = new User(name, age, email, encodedPassword);

        User savedUser = userRepository.save(users);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(),savedUser.getAge(),savedUser.getCreatedAt(),
                savedUser.getModifiedAt());

    }

    // service) 이름으로 유저들 조회
    @Override
    public List<UserResponseDto> findUserByName(String name) {

        return userRepository.findUserByName(name).stream().map(UserResponseDto::new).toList();

    }

    // service) 아이디로 유저 조회
    @Override
    public UserResponseDto findUserById(Long id) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(), findUser.getCreatedAt(),
                findUser.getModifiedAt());

    }

    // service) 유저 수정
    @Override
    public UserResponseDto updateUser(Long loginUserId, Long id , String name, Integer age,
                                            String email, String password, String newPassword, String checkNewPassword) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        sessionCheck(findUser,loginUserId);

        notNullUpdate(findUser, name, age, email, password, newPassword, checkNewPassword);

        userRepository.save(findUser);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(),
                findUser.getCreatedAt(),findUser.getModifiedAt());

    }

    // service) 유저 삭제
    @Override
    public void deleteUser(Long loginUserId,Long id, String password) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        sessionCheck(findUser,loginUserId);

        passwordMatch(password, findUser);

        userRepository.delete(findUser);
    }

    // 패스워드 매치 확인 메서드
    private void passwordMatch(String password, User users) {

        if (!passwordEncoder.matches(password,users.getPassword())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"원래의 비밀번호와 일치하지 않습니다.");
        }

    }

    // 패스워드 같은지 확인 메서드
    private void passwordCheck(String Password,String checkPassword){

        if (!Password.equals(checkPassword)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호와 비밀번호 확인이 일치하지 않습니다");
        }

    }

    // 세션의 id와 요청받은 id 확인 메서드
    private void sessionCheck(User findUser, Long loginUserId) {

        if(!findUser.isSameUser(loginUserId)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "접근 권한이 없습니다");
        }
    }

    // null 아닌 부분 수정 메서드
    private void notNullUpdate(User findUser, String name, Integer age, String email, String password,
                               String newPassword, String checkNewPassword ) {

        if (name != null) {
            findUser.setName(name);
        }

        if (age != null) {
            findUser.setAge(age);
        }

        // 널은 허용, 빈 문자열은 허용X
        if (email != null && !email.isEmpty()) {
            findUser.setEmail(email);
        }

        // 둘 다 넣어야 변경
        if (newPassword != null && checkNewPassword != null) {

            passwordMatch(password, findUser);

            passwordCheck(newPassword,checkNewPassword);

            findUser.setPassword(passwordEncoder.encode(newPassword));
        }

    }
}
