package com.workintech.twitter.service;

import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.exceptions.TwitterException;
import com.workintech.twitter.repository.TweetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TweetsServiceImpl implements TweetsService{

    private TweetsRepository tweetsRepository;
    @Autowired
    public TweetsServiceImpl(TweetsRepository tweetsRepository) {
        this.tweetsRepository = tweetsRepository;
    }

    @Override
    public Tweets save(Tweets tweet) {
        return tweetsRepository.save(tweet);
    }

    @Override
    public List<Tweets> findAll() {
        return tweetsRepository.findAll();
    }

    @Override
    public Tweets find(long id) {
        Optional<Tweets> optionalTweet = tweetsRepository.findById(id);
        if (optionalTweet.isPresent()){
            return optionalTweet.get();
        }
        throw new TwitterException("Tweet with given id is not exist : " + id, HttpStatus.NOT_FOUND);

    }

    @Override
    public Tweets delete(long id) {
        Tweets tweet = find(id);
        tweetsRepository.delete(tweet);
        return tweet;
    }
}
