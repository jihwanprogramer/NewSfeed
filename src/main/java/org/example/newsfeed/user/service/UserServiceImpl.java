package org.example.newsfeed.user.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.newsfeed.board.repository.BoardRepository;
import org.example.newsfeed.common.config.PasswordEncoder;
import org.example.newsfeed.exception.AlreadyExistsException;
import org.example.newsfeed.exception.MisMatchPasswordException;
import org.example.newsfeed.follow.repository.FollowRepository;
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
    private final BoardRepository boardRepository;
    private final FollowRepository followRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * 회원가입을 처리하는 서비스 메서드입니다.
     *
     * @param name 이름
     * @param age 나이
     * @param email 이메일
     * @param password 비밀번호
     * @param checkPassword 비밀번호 확인
     * @return 생성된 사용자 정보를 담은 DTO
     * @throws AlreadyExistsException 이메일 중복 시 예외 발생
     */
    @Override
    @Transactional
    public UserResponseDto signUp(String name, Integer age, String email, String password, String checkPassword) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistsException("이미 있는 이메일입니다");
        }

        passwordCheck(password, checkPassword);

        String encodedPassword = passwordEncoder.encode(password);
        User users = User.of(name, age, email, encodedPassword);
        User savedUser = userRepository.save(users);

        return new UserResponseDto(savedUser.getId(), savedUser.getName(), savedUser.getAge(),
                savedUser.getCreatedAt(), savedUser.getModifiedAt());
    }

    /**
     * 이름으로 사용자 목록을 조회합니다. (List)
     *
     * @param name 조회할 사용자 이름
     * @return 사용자 응답 DTO 리스트
     */
    @Override
    public List<UserResponseDto> findUserByName(String name) {
        return userRepository.findUserByName(name)
                .stream()
                .map(UserResponseDto::new)
                .toList();
    }

    /**
     * 이름으로 사용자 목록을 조회합니다. (Page)
     *
     * @param name 조회할 사용자 이름
     * @param pageable 페이지 정보
     * @return 사용자 응답 DTO 페이지 객체
     */
    @Override
    public Page<UserResponseDto> findPageUserByName(String name, Pageable pageable) {
        return userRepository.findUserByName(name, pageable).map(UserResponseDto::new);
    }

    /**
     * ID로 사용자 정보를 조회합니다.
     *
     * @param id 사용자 ID
     * @return 사용자 응답 DTO
     */
    @Override
    public UserResponseDto findUserById(Long id) {
        User findUser = userRepository.findUserByIdOrElseThrow(id);
        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(),
                findUser.getCreatedAt(), findUser.getModifiedAt());
    }

    /**
     * 사용자 정보를 수정합니다.
     *
     * @param loginUserId 로그인한 사용자 ID (권한 확인용)
     * @param id 수정할 사용자 ID
     * @param name 변경할 이름
     * @param age 변경할 나이
     * @param email 변경할 이메일
     * @param password 기존 비밀번호
     * @param newPassword 새로운 비밀번호
     * @param checkNewPassword 새로운 비밀번호 확인
     * @return 수정된 사용자 응답 DTO
     */
    @Override
    @Transactional
    public UserResponseDto updateUser(Long loginUserId, Long id, String name, Integer age,
                                      String email, String password, String newPassword, String checkNewPassword) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        findUser.sessionCheck(loginUserId);

        if (newPassword != null && checkNewPassword != null) {
            passwordCheck(newPassword, checkNewPassword);
        }

        findUser.notNullUpdate(name, age, email, password, newPassword, checkNewPassword, passwordEncoder);

        userRepository.save(findUser);

        return new UserResponseDto(findUser.getId(), findUser.getName(), findUser.getAge(),
                findUser.getCreatedAt(), findUser.getModifiedAt());
    }

    /**
     * 사용자를 삭제합니다. (팔로우, 게시글도 함께 삭제)
     *
     * @param loginUserId 로그인한 사용자 ID (권한 확인용)
     * @param id 삭제할 사용자 ID
     * @param password 비밀번호 확인용
     */
    @Override
    @Transactional
    public void deleteUser(Long loginUserId, Long id, String password) {

        User findUser = userRepository.findUserByIdOrElseThrow(id);

        findUser.sessionCheck(loginUserId);

        findUser.passwordMatch(password, passwordEncoder);

        followRepository.deleteAllByFollowerId(id);
        followRepository.deleteAllByFollowingUser(findUser);
        boardRepository.deleteAllByUser(findUser);

        userRepository.delete(findUser);
    }

    /**
     * 비밀번호와 비밀번호 확인 값이 일치하는지 확인합니다.
     *
     * @param password 비밀번호
     * @param checkPassword 비밀번호 확인
     * @throws MisMatchPasswordException 비밀번호 불일치 시 예외 발생
     */
    private void passwordCheck(String password, String checkPassword) {
        if (!password.equals(checkPassword)) {
            throw new MisMatchPasswordException("비밀번호와 비밀번호 확인이 일치하지 않습니다");
        }
    }
}
