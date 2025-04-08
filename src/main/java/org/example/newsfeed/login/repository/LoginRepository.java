package org.example.newsfeed.login.repository;

import org.example.newsfeed.exception.LoginAuthException;
import org.example.newsfeed.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Users, Long> {

    //email을 이용한 조회
    Optional<Users> findUsersByEmail(String email);

    default Users findUsersByEmailOrElseThrow(String email){
        return findUsersByEmail(email).orElseThrow(() ->
                new LoginAuthException("아이디가 없거나 비밀번호가 틀립니다.")
        );
    }


}
