package com.workintech.twitter.controller;

import com.workintech.twitter.dto.TweetsDto;
import com.workintech.twitter.entity.Tweets;
import com.workintech.twitter.entity.Users;
import com.workintech.twitter.service.TweetsService;
import com.workintech.twitter.service.UsersService;
import com.workintech.twitter.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tweets")
public class TweetsController {

    private TweetsService tweetsService;
    private UsersService usersService;
    @Autowired
    public TweetsController(TweetsService tweetsService,UsersService usersService) {
        this.tweetsService = tweetsService;
        this.usersService = usersService;
    }

    @PostMapping("/")
    public Tweets save(@RequestBody Tweets tweet, @RequestParam("userId") long userId) {
        Users existUser = usersService.find(userId);
        if (existUser != null) {
            tweet.setUser(existUser);
            return tweetsService.save(tweet);
        } else {
            throw new IllegalArgumentException("Invalid user ID");
        }
    }


    @GetMapping("/")
    public List<TweetsDto> findAll(){
        List<Tweets> tweets = tweetsService.findAll();
        List<TweetsDto> tweetsDtoList = new ArrayList<>();

        for (Tweets tweet: tweets){
            TweetsDto tweetsDto = EntityConverter.tweetResults(tweet);
            tweetsDtoList.add(tweetsDto);
        }
        return tweetsDtoList;
    }

    @GetMapping("/{id}")
    public TweetsDto find(@PathVariable long id){
        Tweets tweet = tweetsService.find(id);
        return EntityConverter.tweetResults(tweet);
    }

    @DeleteMapping("/{id}")
    public TweetsDto delete(@PathVariable long id){
        Tweets deletedTweet = tweetsService.delete(id);
        return EntityConverter.tweetResults(deletedTweet);
    }

}
