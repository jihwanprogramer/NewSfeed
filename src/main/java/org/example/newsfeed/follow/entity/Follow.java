package org.example.newsfeed.follow.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.example.newsfeed.user.entity.Users;

@Entity
@Getter
@Table(name = "follow")
public class Follow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean followYN;

    @Column
    private Long followingId;

    @ManyToOne
    @JoinColumn(name = "follow_id")
    private Users followusers;

    public Follow(boolean followYN, Long followingId) {
        this.followYN = followYN;
        this.followingId = followingId;
    }

    public void setFollowUsers(Users followusers){
        this.followusers = followusers;
    }


}
