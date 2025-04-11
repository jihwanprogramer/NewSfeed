package org.example.newsfeed.follow.service;

import lombok.RequiredArgsConstructor;
import org.example.newsfeed.exception.AccessDeniedException;
import org.example.newsfeed.exception.AlreadyExistsException;
import org.example.newsfeed.follow.dto.FollowResponseDto;
import org.example.newsfeed.follow.dto.FollowSingleResponseDto;
import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.follow.repository.FollowRepository;
import org.example.newsfeed.user.entity.User;
import org.example.newsfeed.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
/**
 * 팔로우 기능의 비즈니스 로직을 처리하는 서비스 구현체입니다.
 */
@Service
@RequiredArgsConstructor
public class FollowServiceImpl implements FollowService {

    private final UserRepository userRepository;
    private final FollowRepository followRepository;

    /**
     * 사용자가 다른 사용자를 팔로우합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 생성된 팔로우 정보 DTO
     * @throws AlreadyExistsException 이미 팔로우가 존재할 경우 예외 발생
     */
    @Transactional
    @Override
    public FollowSingleResponseDto createFollow(Long followerId, Long followingId) {
        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);
        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUser(followerId, followingUser);
        optionalFollow.ifPresent(f -> { throw new AlreadyExistsException("이미 팔로우 내역이 존재합니다."); });

        Follow follow = Follow.of(followerId, followingUser);
        followRepository.save(follow);
        followRepository.flush();

        int followerCount = countFollowByFollowingId(followingId, followerId);
        int followingCount = countFollowByFollowerId(followerId, followingId);

        return new FollowSingleResponseDto(follow.getId(), follow.isFollowYN(), followerCount, followingCount, false);
    }

    /**
     * 사용자의 팔로우 상태를 토글합니다. (팔로우 ↔ 언팔로우)
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 변경된 팔로우 정보 DTO
     */
    @Transactional
    @Override
    public FollowSingleResponseDto updateFollow(Long followerId, Long followingId) {
        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);
        Follow follow = followRepository.findByFollowerIdAndFollowingUserOrElseThrow(followerId, followingUser);

        follow.updateFollow();
        followRepository.flush();

        int followerCount = countFollowByFollowingId(followingId, followerId);
        int followingCount = countFollowByFollowerId(followerId, followingId);

        return new FollowSingleResponseDto(follow.getId(), follow.isFollowYN(), followerCount, followingCount, false);
    }

    /**
     * 로그인한 사용자가 특정 사용자를 팔로우 중인지 확인합니다.
     * 자기 자신일 경우 isMyProfile 값을 true로 설정합니다.
     *
     * @param followerId 로그인한 사용자 ID
     * @param followingId 팔로우 대상 사용자 ID
     * @return 팔로우 상태 정보 DTO
     */
    @Override
    public FollowSingleResponseDto findFollowStatus(Long followerId, Long followingId) {
        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);

        int followerCount = countFollowByFollowingId(followingId, followerId);
        int followingCount = countFollowByFollowerId(followerId, followingId);

        if (followerId.equals(followingId)) {
            return new FollowSingleResponseDto(null, false, followerCount, followingCount, true);
        }

        Follow follow = followRepository.findByFollowerIdAndFollowingUserOrElseThrow(followerId, followingUser);
        return new FollowSingleResponseDto(follow.getId(), follow.isFollowYN(), followerCount, followingCount, false);
    }

    /**
     * 사용자가 팔로우한 사용자 목록을 조회합니다. (Following 목록)
     * 자신이 아닌 경우, 접근 권한을 검증합니다.
     *
     * @param id 대상 사용자 ID
     * @param loginUserId 로그인한 사용자 ID
     * @param pageable 페이징 정보
     * @return 팔로우한 사용자 목록 DTO 페이지
     */
    @Override
    public Page<FollowResponseDto> findFollowingsById(Long id, Long loginUserId, Pageable pageable) {
        User followingUser = userRepository.findUserByIdOrElseThrow(id);
        User loginUser = userRepository.findUserByIdOrElseThrow(loginUserId);
        existFollowTrue(followingUser.getId(), loginUser);

        return followRepository.findByFollowerId(id, pageable).map(FollowResponseDto::toDto);
    }

    /**
     * 사용자를 팔로우한 사용자 목록을 조회합니다. (Follower 목록)
     * 자신이 아닌 경우, 접근 권한을 검증합니다.
     *
     * @param id 대상 사용자 ID
     * @param loginUserId 로그인한 사용자 ID
     * @param pageable 페이징 정보
     * @return 팔로워 목록 DTO 페이지
     */
    @Override
    public Page<FollowResponseDto> findFollowersById(Long id, Long loginUserId, Pageable pageable) {
        User followingUser = userRepository.findUserByIdOrElseThrow(id);
        User loginUser = userRepository.findUserByIdOrElseThrow(loginUserId);
        existFollowTrue(followingUser.getId(), loginUser);

        return followRepository.findByFollowingUser(followingUser, pageable).map(FollowResponseDto::toDto);
    }

    /**
     * 팔로우가 존재하고, 그 상태가 true인지 검증합니다.
     * 자기 자신이 아닌데 팔로우가 없거나 비활성화 상태일 경우 접근을 차단합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingUser 팔로우 대상 사용자
     * @throws AccessDeniedException 권한이 없을 경우 예외 발생
     */
    @Override
    public void existFollowTrue(Long followerId, User followingUser) {
        Optional<Follow> optionalFollow = followRepository.findByFollowerIdAndFollowingUser(followerId, followingUser);

        if (!followerId.equals(followingUser.getId()) &&
                (optionalFollow.isEmpty() || !optionalFollow.get().isFollowYN())) {
            throw new AccessDeniedException("이 유저가 당신을 팔로우 해야 볼 수 있습니다.");
        }
    }

    /**
     * 특정 사용자를 팔로우한 사용자 수를 조회합니다.
     *
     * @param followingId 팔로우 대상 사용자 ID
     * @param loginId 로그인한 사용자 ID
     * @return 팔로워 수
     */
    @Override
    public int countFollowByFollowingId(Long followingId, Long loginId) {
        User followingUser = userRepository.findUserByIdOrElseThrow(followingId);
        return followRepository.countByFollowingUserAndFollowYN(followingUser, true);
    }

    /**
     * 특정 사용자가 팔로우한 사용자 수를 조회합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param loginId 로그인한 사용자 ID
     * @return 팔로잉 수
     */
    @Override
    public int countFollowByFollowerId(Long followerId, Long loginId) {
        return followRepository.countByFollowerIdAndFollowYN(followerId, true);
    }
}
