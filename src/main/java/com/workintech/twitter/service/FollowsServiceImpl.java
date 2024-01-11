package com.workintech.twitter.service;

import com.workintech.twitter.entity.Follows;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.repository.FollowsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowsServiceImpl implements FollowsService{

    private FollowsRepository followsRepository;
    @Autowired
    public FollowsServiceImpl(FollowsRepository followsRepository) {
        this.followsRepository = followsRepository;
    }

    @Override
    public void followUser(long followerId, long followingId) {
        Users follower = new Users();
        follower.setId(followerId);

        Users following = new Users();
        following.setId(followingId);

        Follows follow = new Follows();
        follow.setFollowersId(follower);
        follow.setFollowingId(following);

        followsRepository.save(follow);
    }

    @Override
    public void unfollowUser(long followerId, long followingId) {

    }
}
