package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comments;

import java.util.List;

public interface CommentsService {
    Comments addCommentToTweet(Comments comment, long tweetId, long userId);
    List<Comments> getCommentsByTweetId(long tweetId);
    void deleteCommentFromTweet(long tweetId, long commentId);
}
