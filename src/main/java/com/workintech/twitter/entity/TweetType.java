package com.workintech.twitter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tweet_type", schema = "fsweb")
public class TweetType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type_name")
    private TypeName typeName;

    @OneToMany(mappedBy = "tweetType", cascade = CascadeType.ALL)
    private List<Tweets> tweets;

    public void addTweet(Tweets tweet){
        if (tweets == null){
            tweets = new ArrayList<>();
        }
        tweets.add(tweet);
    }
}
