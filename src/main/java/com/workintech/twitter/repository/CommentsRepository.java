package com.workintech.twitter.repository;

import com.workintech.twitter.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments, Long> {

    List<Comments> findByTweetId(long tweetId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Comments c WHERE c.tweet.id = :tweetId AND c.id = :commentId")
    void deleteByTweetIdAndId(long tweetId, long commentId);
}
