package org.example.newsfeed.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.common.entity.BaseEntity;
import org.example.newsfeed.exception.SelfFollowNotAllowedException;
import org.example.newsfeed.user.entity.User;

/**
 * 팔로우 엔티티입니다.
 */
@Entity
@Getter
@Table(name = "follow")
public class Follow extends BaseEntity {

    /**
     * 팔로우 식별자.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 팔로우 여부.
     */
    @Column(nullable = false)
    private boolean followYN;

    /**
     * 팔로우 요청자 ID.
     */
    @Column
    private Long followerId;

    /**
     * 팔로우 대상 사용자.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User followingUser;

    public Follow() {
    }

    /**
     * Follow 객체의 생성자입니다.
     *
     * @param followYN 팔로우 여부
     * @param followerId 팔로우 요청자 ID
     */
    private Follow(boolean followYN, Long followerId) {
        this.followYN = followYN;
        this.followerId = followerId;
    }

    /**
     * 팔로우 대상 사용자를 초기화합니다.
     *
     * @param followingUser 팔로우 대상 사용자
     */
    private void initFollowUser(User followingUser){
        this.followingUser = followingUser;
    }

    /**
     * 팔로우 상태를 토글합니다.
     *
     * @return 변경된 팔로우 상태
     */
    public boolean updateFollow() {
        this.followYN = !this.followYN;
        return this.followYN;
    }

    /**
     * Follow 객체를 생성하는 정적 팩토리 메서드입니다.
     *
     * @param followerId 팔로우 요청자 ID
     * @param followingUser 팔로우 대상 사용자
     * @return 생성된 Follow 객체
     * @throws SelfFollowNotAllowedException 자기 자신을 팔로우할 경우 발생
     */
    public static Follow createFollow(Long followerId, User followingUser) {
        if (followerId.equals(followingUser.getId())) {
            throw new SelfFollowNotAllowedException("자신은 팔로우 할 수 없습니다.");
        }
        Follow follow = new Follow(true, followerId);
        follow.initFollowUser(followingUser);
        return follow;
    }


}
