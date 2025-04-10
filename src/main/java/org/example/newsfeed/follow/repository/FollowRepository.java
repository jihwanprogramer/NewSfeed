package org.example.newsfeed.follow.repository;

import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerIdAndFollowingUser(Long followingID, User followUser);
    List<Follow> findByFollowerId(Long followerId);
    List<Follow> findByFollowingUser(User followingUser);
    int countByFollowerIdAndFollowYN(Long followerId,boolean followYN);
    int countByFollowingUserAndFollowYN(User followingUser,boolean followYN);

}
