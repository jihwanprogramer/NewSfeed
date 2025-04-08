package org.example.newsfeed.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.user.entity.BaseEntity;
import org.example.newsfeed.user.entity.Users;

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
    private Long followingId;

    @ManyToOne
    @JoinColumn(name = "follow_id")
    private Users followUsers;

    public Follow() {
    }

    public Follow(boolean followYN, Long followingId) {
        this.followYN = followYN;
        this.followingId = followingId;
    }

    public void setFollowUsers(Users followUsers){
        this.followUsers = followUsers;
    }

    public boolean updateFollow() {
        this.followYN = !this.followYN;
        return this.followYN;
    }


}
