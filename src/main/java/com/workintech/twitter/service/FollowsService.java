package com.workintech.twitter.service;

public interface FollowsService {

    void followUser(long followerId, long followingId);
    void unfollowUser(long followerId, long followingId);
}
