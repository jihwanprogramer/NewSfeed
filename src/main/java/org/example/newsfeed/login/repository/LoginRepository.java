package org.example.newsfeed.login.repository;

import org.example.newsfeed.exception.LoginAuthException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<User, Long> {

    //email을 이용한 조회
    Optional<User> findMemberByEmail(String email);

    default User findMemberByEmailOrElseThrow(String email){
        return findMemberByEmail(email).orElseThrow(() ->
                new LoginAuthException("아이디가 없거나 비밀번호가 틀립니다.")
        );
    }


}
