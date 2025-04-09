package org.example.newsfeed.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.exception.AlreadyExistsEsception;
import org.example.newsfeed.exception.NullResponseException;
import org.example.newsfeed.exception.SelfFollowNotAllowedException;
import org.example.newsfeed.follow.dto.FollowCountResponseDto;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;
import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.follow.repository.FollowRepository;
import org.example.newsfeed.user.entity.User;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService{

    private final UserRepository userRepository;
    private final FollowRepository followRepository;



    @Override
    public FollowSingleResponseDto saveFollow(Long followerId, Long followingId) {

        if(followerId.equals(followingId)){
            throw new SelfFollowNotAllowedException("자신은 팔로우 할 수 없습니다.");
        }

        User followingUsers = userRepository.findUserByIdOrElseThrow(followingId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUser(followerId, followingUsers);
        if(optionalFollow.isPresent()){
            throw new AlreadyExistsEsception("이미 팔로우 내역이 존재합니다.");
        }

        Follow follow = new Follow(true, followerId);
        follow.setFollowUser(followingUsers);

        followRepository.save(follow);

        return new FollowSingleResponseDto(follow.getId(), follow.isFollowYN(), false);

    }

    @Transactional
    @Override
    public FollowSingleResponseDto updateFollow(Long followerId, Long followingId) {

        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUser(followerId, followingUser);
        if(optionalFollow.isEmpty()){
            throw new NullResponseException("팔로우 내역이 존재하지 않습니다.");
        }

        optionalFollow.get().updateFollow();

        Follow updatedFollow = optionalFollow.get();

        return new FollowSingleResponseDto(updatedFollow.getId(), updatedFollow.isFollowYN(), false);
    }

    @Override
    public FollowSingleResponseDto findFollowStatus(Long followerId, Long followingId) {

        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);

        if (followerId == followingId){
            return new FollowSingleResponseDto(null, false, true);
        }

        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUser(followerId, followingUser);
        if(optionalFollow.isEmpty()){
            throw new NullResponseException("팔로우 내역이 없습니다.");
        }

        return new FollowSingleResponseDto(optionalFollow.get().getId(), optionalFollow.get().isFollowYN(), false);
    }

    @Override
    public List<FollowResponseDto> findFollowingsById(Long id) {

        return followRepository.findByFollowerId(id).stream().map(FollowResponseDto::toDto).toList();
    }

    @Override
    public List<FollowResponseDto> findFollowersById(Long id) {

        User user = userRepository.findUserByIdOrElseThrow(id);

        return followRepository.findByFollowingUser(user).stream().map(FollowResponseDto::toDto).toList();
    }

    @Override
    public boolean existFollowTrue(Long followerId, Long followingId) {

        User followingUsers = userRepository.findUserByIdOrElseThrow(followerId);
        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUser(followerId, followingUsers);
        return optionalFollow.isPresent() && optionalFollow.get().isFollowYN();
    }

    @Override
    public FollowCountResponseDto countFollowByFollowingId(Long followingId, Long loginId) {

        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);
        Long count = followRepository.countByFollowingUserAndFollowYN(followingUser, true);
        return new FollowCountResponseDto(count, followingId.equals(loginId));

    }

    @Override
    public FollowCountResponseDto countFollowByFollowerId(Long followerId, Long loginId) {

        Long count = followRepository.countByFollowerIdAndFollowYN(followerId, true);
        return new FollowCountResponseDto(count, followerId.equals(loginId));

    }


}
