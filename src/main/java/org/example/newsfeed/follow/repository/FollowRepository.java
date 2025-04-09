package org.example.newsfeed.follow.repository;

import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerIdAndFollowingUsers(Long followingID, Users followingUsers );
    List<Follow> findByFollowerId(Long followerId);
    List<Follow> findByFollowingUsers(Users followingUsers);
    Long countByFollowerId(Long followerID);
    Long countByFollowingUsers(Users followingUsers);


}
