package org.example.newsfeed.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.exception.NullResponseException;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.follow.repository.FollowRepository;
import org.example.newsfeed.user.entity.Users;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final UserRepository userRepository;
    private final FollowRepository followRepository;


    @Override
    public void saveFollow(Long followerId, Long followingId) {

        Users followingUsers = userRepository.findUserByIdOrElseThrow(followerId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUsers(followerId, followingUsers);
        if(!optionalFollow.isEmpty()){
            throw new AlreadyExistsEsception("이미 팔로우 내역이 존재합니다.");
        }

        Follow follow = new Follow(true, followerId);
        follow.setFollowUsers(followingUsers);

        followRepository.save(follow);

    }

    @Override
    public boolean updateFollow(Long followerId, Long followingId) {

        Users followingUsers = userRepository.findUserByIdOrElseThrow(followerId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUsers(followerId, followingUsers);
        if(optionalFollow.isEmpty()){
            throw new NullResponseException("팔로우 내역이 존재하지 않습니다.");
        }

        return optionalFollow.get().updateFollow();
    }

    @Override
    public List<FollowResponseDto> findFollowingsByMyId(Long myId) {

        return followRepository.findByFollowerId(myId).stream().map(FollowResponseDto::toDto).toList();
    }

    @Override
    public List<FollowResponseDto> findFollowersByMyId(Long myId) {

        Users users = userRepository.findUserByIdOrElseThrow(myId);

        return followRepository.findByFollowingUsers(users).stream().map(FollowResponseDto::toDto).toList();
    }


}
