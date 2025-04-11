package org.example.newsfeed.login.repository;

import org.example.newsfeed.exception.LoginAuthException;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * 로그인 관련 사용자 정보를 조회하는 리포지토리입니다.
 */
public interface LoginRepository extends JpaRepository<User, Long> {

    /**
     * 이메일을 이용하여 사용자 정보를 조회합니다.
     *
     * @param email 사용자 이메일
     * @return 사용자 정보 Optional
     */
    Optional<User> findUsersByEmail(String email);

    /**
     * 이메일을 이용하여 사용자 정보를 조회하고, 없을 경우 예외를 발생시킵니다.
     *
     * @param email 사용자 이메일
     * @return 조회된 사용자 정보
     * @throws LoginAuthException 이메일에 해당하는 사용자가 없거나 비밀번호가 틀릴 경우
     */
    default User findUsersByEmailOrElseThrow(String email) {
        return findUsersByEmail(email).orElseThrow(() ->
                new LoginAuthException("아이디가 없거나 비밀번호가 틀립니다.")
        );
    }
}
