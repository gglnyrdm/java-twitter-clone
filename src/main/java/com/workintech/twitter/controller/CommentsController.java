package com.workintech.twitter.controller;

import com.workintech.twitter.dto.CommentsDto;
import com.workintech.twitter.entity.Comments;
import com.workintech.twitter.service.CommentsService;
import com.workintech.twitter.service.TweetsService;
import com.workintech.twitter.service.UsersService;
import com.workintech.twitter.util.EntityConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentsController {

    private CommentsService commentsService;
    @Autowired
    public CommentsController(CommentsService commentsService, UsersService usersService, TweetsService tweetsService) {
        this.commentsService = commentsService;
    }

    @PostMapping("/add/{tweetId}/{userId}")
    public Comments addCommentToTweet(@RequestBody Comments comment, @PathVariable long tweetId, @PathVariable long userId){
        return commentsService.addCommentToTweet(comment,tweetId, userId);
    }

    @GetMapping("/get/{tweetId}")
    public List<CommentsDto> getCommentsByTweetId(@PathVariable long tweetId){
        List<Comments> comments = commentsService.getCommentsByTweetId(tweetId);
        List<CommentsDto> commentsDtoList = new ArrayList<>();

        for (Comments comment: comments){
            CommentsDto commentsDto = EntityConverter.commentResults(comment);
            commentsDtoList.add(commentsDto);
        }
        return commentsDtoList;
    }

}


