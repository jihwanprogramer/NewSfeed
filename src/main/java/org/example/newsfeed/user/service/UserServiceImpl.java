package org.example.newsfeed.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.exception.AlreadyExistsException;
import org.example.newsfeed.exception.MisMatchPasswordException;
import org.example.newsfeed.exception.MisMatchUserException;
import org.example.newsfeed.exception.WrongPasswordException;
import org.example.newsfeed.user.dto.UserResponseDto;
import org.example.newsfeed.user.entity.User;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // service) 회원가입
    @Override
    @Transactional
    public UserResponseDto signUp(String name, Integer age, String email, String password, String checkPassword) {

        // 중복 이메일 체크
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistsException("이미 있는 이메일입니다");
        }

        passwordCheck(password,checkPassword);

        String encodedPassword = passwordEncoder.encode(password);

        User users = User.of(name,age,email,encodedPassword);

        User savedUser = userRepository.save(users);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(),savedUser.getAge(),savedUser.getCreatedAt(),
                savedUser.getModifiedAt());

    }

    // service) 이름으로 유저들 조회(List)
    @Override
    public List<UserResponseDto> findUserByName(String name) {

        return userRepository.findUserByName(name)
                .stream()
                .map(UserResponseDto::new)
                .toList();

    }

    // service) 이름으로 유저들 조회(Page)
    @Override
    public Page<UserResponseDto> findPageUserByName(String name, Pageable pageable) {

        return userRepository.findUserByName(name, pageable).map(UserResponseDto::new);

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
    @Transactional
    public UserResponseDto updateUser(Long loginUserId, Long id , String name, Integer age,
                                            String email, String password, String newPassword, String checkNewPassword) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        sessionCheck(findUser,loginUserId);

        if (newPassword != null && checkNewPassword != null) {
            passwordCheck(newPassword,checkNewPassword);
        }

        findUser.notNullUpdate(name,age,email, password, newPassword, checkNewPassword,passwordEncoder);

        userRepository.save(findUser);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(),
                findUser.getCreatedAt(),findUser.getModifiedAt());

    }

    // service) 유저 삭제
    @Override
    @Transactional
    public void deleteUser(Long loginUserId,Long id, String password) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        sessionCheck(findUser,loginUserId);

        findUser.passwordMatch(password, passwordEncoder);

        userRepository.delete(findUser);
    }

    // 패스워드 같은지 확인 메서드
    public void passwordCheck(String Password,String checkPassword){

        if (!Password.equals(checkPassword)) {
            throw new MisMatchPasswordException("비밀번호와 비밀번호 확인이 일치하지 않습니다");
        }

    }

    // 세션의 id와 요청받은 id 확인 메서드
    private void sessionCheck(User findUser, Long loginUserId) {

        if(!findUser.isSameUser(loginUserId)){
            throw new MisMatchUserException("권한이 없습니다.");
        }
    }

}
