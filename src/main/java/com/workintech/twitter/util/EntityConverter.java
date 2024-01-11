package com.workintech.twitter.util;

import com.workintech.twitter.dto.CommentsDto;
import com.workintech.twitter.dto.TweetsDto;
import com.workintech.twitter.dto.UsersDto;
import com.workintech.twitter.entity.Comments;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;

public class EntityConverter {
    public static UsersDto findResults(Users user){
        return new UsersDto(user.getId(), user.getUsername(), user.getEmail(),
                user.getFirstName(), user.getLastName(), user.getCreatedAt());
    }

    public static TweetsDto tweetResults(Tweets tweet){
        return new TweetsDto(tweet.getId(), tweet.getContent(), tweet.getUser().getId(),
                tweet.getCreatedAt());
    }

    public static CommentsDto commentResults(Comments comment){
        return new CommentsDto(comment.getId(), comment.getContent(), comment.getUser().getId(),
                comment.getTweet().getId(),comment.getCreatedAt());
    }
}
