package org.example.newsfeed.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.exception.NullResponseException;
import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.follow.repository.FollowRepository;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final UserRepository userRepository;
    private final FollowRepository followRepository;


    @Override
    public void saveFollow(Long followId, Long followingId) {

        Users followUsers = userRepository.findUserByIdOrElseThrow(followId);

        Optional<Follow> optionalFollow = followRepository.findByFollowingIdAndFollowUsers(followId, followUsers);
        if(!optionalFollow.isEmpty()){
            throw new AlreadyExistsEsception("이미 팔로우 내역이 존재합니다.");
        }

        Follow follow = new Follow(true, followingId);
        follow.setFollowUsers(followUsers);

        followRepository.save(follow);

    }

    @Override
    public boolean updateFollow(Long followId, Long followingId) {

        Users followUsers = userRepository.findUserByIdOrElseThrow(followId);

        Optional<Follow> optionalFollow = followRepository.findByFollowingIdAndFollowUsers(followId, followUsers);
        if(optionalFollow.isEmpty()){
            throw new NullResponseException("팔로우 내역이 존재하지 않습니다.");
        }

        return optionalFollow.get().updateFollow();
    }


}
