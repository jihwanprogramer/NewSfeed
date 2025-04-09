package org.example.newsfeed.follow.repository;

import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerIdAndFollowingUsers(Long followingID, User followUsers );
    List<Follow> findByFollowerId(Long followerId);
    List<Follow> findByFollowingUsers(User followingUsers);


}
