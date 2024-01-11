package com.workintech.twitter.service;

import com.workintech.twitter.entity.Tweets;

import java.util.List;

public interface TweetsService {
    Tweets save(Tweets tweet);
    List<Tweets> findAll();
    Tweets find(long id);
    Tweets delete(long id);
}
