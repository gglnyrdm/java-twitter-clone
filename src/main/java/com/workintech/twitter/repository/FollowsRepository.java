package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Follows;
import com.workintech.twitter.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowsRepository extends JpaRepository<Follows, Long> {
    void deleteByFollowersIdAndFollowingId(Users followers, Users following);
}
