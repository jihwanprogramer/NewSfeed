package org.example.newsfeed.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.user.entity.BaseEntity;
import org.example.newsfeed.user.entity.User;

@Entity
@Getter
@Table(name = "follow")
public class Follow extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean followYN;

    @Column
    private Long followerId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User followingUser;

    public Follow() {
    }

    public Follow(boolean followYN, Long followerId) {
        this.followYN = followYN;
        this.followerId = followerId;
    }

    public void setFollowUser(User followingUser){
        this.followingUser = followingUser;
    }

    public boolean updateFollow() {
        this.followYN = !this.followYN;
        return this.followYN;
    }


}
