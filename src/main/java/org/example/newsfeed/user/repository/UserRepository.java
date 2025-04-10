package org.example.newsfeed.user.repository;

import org.example.newsfeed.exception.NullResponseException;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findUserByName(String name);
    Optional<User> findUserByEmail(String email);
    Page<User> findUserByName(String name, Pageable pageable);

    default User findUserByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new NullResponseException("id를 가진 회원이 없습니다"));
    }
}
