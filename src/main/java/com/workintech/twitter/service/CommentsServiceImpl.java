package com.workintech.twitter.service;

import com.workintech.twitter.entity.Comments;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.exceptions.TwitterException;
import com.workintech.twitter.repository.CommentsRepository;
import com.workintech.twitter.repository.TweetsRepository;
import com.workintech.twitter.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsServiceImpl implements CommentsService{

    private CommentsRepository commentsRepository;
    private TweetsRepository tweetsRepository;
    private UsersRepository usersRepository;
    @Autowired
    public CommentsServiceImpl(CommentsRepository commentsRepository, TweetsRepository tweetsRepository, UsersRepository usersRepository) {
        this.commentsRepository = commentsRepository;
        this.tweetsRepository = tweetsRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public Comments addCommentToTweet(Comments comment, long tweetId, long userId) {

        Tweets tweet = null;
        Users user = null;

        Optional<Tweets> optionalTweet = tweetsRepository.findById(tweetId);
        if (optionalTweet.isPresent()){
            tweet = optionalTweet.get();
        }else {
            throw new TwitterException("Tweet not found", HttpStatus.NOT_FOUND);
        }

        Optional<Users> optionalUser = usersRepository.findById(userId);
        if (optionalUser.isPresent()){
            user = optionalUser.get();
        }else {
            throw new TwitterException("User not found", HttpStatus.NOT_FOUND);
        }

        comment.setTweet(tweet);
        comment.setUser(user);

        return commentsRepository.save(comment);
    }

    @Override
    public List<Comments> getCommentsByTweetId(long tweetId) {
        return commentsRepository.findByTweetId(tweetId);
    }

    @Override
    public void deleteCommentFromTweet(long tweetId, long commentId) {
        commentsRepository.deleteByTweetIdAndId(tweetId,commentId);
    }
}
