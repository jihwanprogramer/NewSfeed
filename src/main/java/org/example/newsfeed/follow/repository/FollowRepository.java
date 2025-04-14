package org.example.newsfeed.follow.repository;

import org.example.newsfeed.exception.NullResponseException;
import org.example.newsfeed.follow.entity.Follow;
import org.example.newsfeed.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
/**
 * 팔로우 엔티티에 대한 데이터 접근을 담당하는 리포지토리입니다.
 */
public interface FollowRepository extends JpaRepository<Follow, Long> {

    /**
     * 팔로우 요청자 ID와 팔로우 대상 사용자로 팔로우 정보를 조회합니다.
     *
     * @param followingID 팔로우 요청자 ID
     * @param followUser 팔로우 대상 사용자
     * @return 팔로우 정보 Optional
     */
    Optional<Follow> findByFollowerIdAndFollowingUser(Long followingID, User followUser);

    /**
     * 특정 사용자가 팔로우한 사용자 목록을 페이징하여 조회합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param pageable 페이징 정보
     * @return 팔로우 목록 페이지
     */
    Page<Follow> findByFollowerId(Long followerId, Pageable pageable);

    /**
     * 특정 사용자를 팔로우한 사용자 목록을 페이징하여 조회합니다.
     *
     * @param followingUser 팔로우 대상 사용자
     * @param pageable 페이징 정보
     * @return 팔로워 목록 페이지
     */
    Page<Follow> findByFollowingUser(User followingUser, Pageable pageable);

    /**
     * 특정 사용자가 팔로우한 사용자 수를 조회합니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followYN 팔로우 여부
     * @return 팔로우 수
     */
    int countByFollowerIdAndFollowYN(Long followerId, boolean followYN);

    /**
     * 특정 사용자를 팔로우한 사용자 수를 조회합니다.
     *
     * @param followingUser 팔로우 대상 사용자
     * @param followYN 팔로우 여부
     * @return 팔로워 수
     */
    int countByFollowingUserAndFollowYN(User followingUser, boolean followYN);

    /**
     * 팔로우 요청자 ID와 팔로우 대상 사용자로 팔로우 정보를 조회하고, 존재하지 않을 경우 예외를 발생시킵니다.
     *
     * @param followingID 팔로우 요청자 ID
     * @param followUser 팔로우 대상 사용자
     * @return 팔로우 정보
     * @throws NullResponseException 팔로우 정보가 존재하지 않을 경우 예외 발생
     */
    default Follow findByFollowerIdAndFollowingUserOrElseThrow(Long followingID, User followUser) {
        return findByFollowerIdAndFollowingUser(followingID, followUser)
                .orElseThrow(() -> new NullResponseException("팔로우 내역이 없습니다"));
    }

    void deleteAllByFollowerId(Long followerId);

    void deleteAllByFollowingUser(User followingUser);
}
