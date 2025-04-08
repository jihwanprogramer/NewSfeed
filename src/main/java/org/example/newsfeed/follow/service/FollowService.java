package org.example.newsfeed.follow.service;

public interface FollowService {

    void saveFollow(Long followId, Long followingId);
    boolean updateFollow(Long followId, Long followingId);
}
