package org.example.newsfeed.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.follow.repository.FollowRepository;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final UserRepository userRepository;
    private final FollowRepository followRepository;


    @Override
    public void saveFollow(Long followId, Long followingId) {

        Users followusers = userRepository.findUserByIdOrElseThrow(followId);

        Follow follow = new Follow(true, followingId);
        follow.setFollowUsers(followusers);

        followRepository.save(follow);

    }



}
